package io.beaniejoy.dongnecafe.domain.common.utils.security

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.common.config.property.JwtTokenProperties
import io.beaniejoy.dongnecafe.domain.common.utils.security.SecurityConstant.JWT_AUTHORITIES_KEY
import io.beaniejoy.dongnecafe.domain.common.utils.security.SecurityConstant.JWT_AUTHORITY_DELIMITER
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import mu.KLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtils(private val jwtTokenProperties: JwtTokenProperties) {
    companion object : KLogging()

    /**
     * access, refresh token 신규 발급
     * @param authToken AuthToken? 기존 저장된 AuthToken entity (없으면 신규 생성)
     * @param authentication Authentication 인증된 auth 객체
     * @return AuthToken
     */
    fun createOrUpdateNewToken(authToken: AuthToken?, authentication: Authentication): AuthToken {
        val authenticatedMember = authentication.getMember()
        val authorities = authentication.authorities.joinToString(JWT_AUTHORITY_DELIMITER) { it.authority }

        val nowTime = Date()

        jwtTokenProperties.also {
            val accessToken = generateToken(authenticatedMember.id, authorities, nowTime, AuthTokenType.ACCESS)
            val refreshToken = generateToken(authenticatedMember.id, authorities, nowTime, AuthTokenType.REFRESH)

            return createOrUpdateAuthToken(
                authToken = authToken,
                member = authenticatedMember,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

    fun generateToken(
        authenticatedMemberId: Long,
        authorities: String,
        baseDate: Date,
        tokenType: AuthTokenType
    ): String {
        val tokenProperties = tokenType.getTokenConfigProperties(jwtTokenProperties)
        val expirationDate = Date(baseDate.time + tokenProperties.getValidityTimeWithMilliSec())

        return Jwts.builder()
            .setSubject(authenticatedMemberId.toString())
            .claim(JWT_AUTHORITIES_KEY, authorities)
            .setIssuedAt(baseDate)
            .signWith(tokenProperties.getGeneratedKey(), SignatureAlgorithm.HS256)
            .setExpiration(expirationDate)
            .compact()
    }

    /**
     * 기존 authToken entity 존재시 token update
     * 기존 authToken entity 없으면 신규 entity create
     */
    private fun createOrUpdateAuthToken(
        authToken: AuthToken?,
        member: Member,
        accessToken: String,
        refreshToken: String
    ): AuthToken {
        return authToken?.apply {
            this.updateTokens(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        } ?: AuthToken.createEntity(
            member = member,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun getAuthentication(authToken: String, tokenType: AuthTokenType): Authentication? {
        val claims = getValidTokenBody(authToken, tokenType)
            ?: return null

        val authorities = claims[JWT_AUTHORITIES_KEY].toString()
            .split(JWT_AUTHORITY_DELIMITER)
            .map { SimpleGrantedAuthority(it) }

        return UsernamePasswordAuthenticationToken(claims.subject, authToken, authorities)
    }

    // jwt access token 유효성 검증 및 claims 획득
    private fun getValidTokenBody(accessToken: String, tokenType: AuthTokenType): Claims? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(
                    tokenType
                        .getTokenConfigProperties(jwtTokenProperties)
                        .getGeneratedKey()
                )
                .build()
                .parseClaimsJws(accessToken)
                .body
        } catch (e: ExpiredJwtException) {
            logger.info { "JWT access token expired. > Error: ${e.message}" }
            null
        } catch (e: Exception) {
            logger.info { "JWT access token invalid. > Error: ${e.message}" }
            null
        }
    }

    // token 만료 체크
    fun checkTokenExpired(authToken: String, tokenType: AuthTokenType): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(
                    tokenType
                        .getTokenConfigProperties(jwtTokenProperties)
                        .getGeneratedKey()
                )
                .build()
                .parseClaimsJws(authToken)

            false
        } catch (e: ExpiredJwtException) {
            true
        } catch (e: Exception) {
            false
        }
    }
}
