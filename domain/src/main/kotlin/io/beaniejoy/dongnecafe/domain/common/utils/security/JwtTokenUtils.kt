package io.beaniejoy.dongnecafe.domain.common.utils.security

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.common.config.property.JwtTokenProperties
import io.beaniejoy.dongnecafe.domain.common.error.exception.auth.UnauthorizedMemberException
import io.beaniejoy.dongnecafe.domain.common.utils.security.SecurityConstant.JWT_AUTHORITIES_KEY
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
    companion object : KLogging() {
        const val JWT_AUTHORITY_DELIMITER = ","
    }

    /**
     * access, refresh token 신규 발급
     * @param authentication Authentication 인증된 auth 객체
     * @return AuthToken(redis cache)
     */
    fun createNewAuthToken(authentication: Authentication): AuthToken {
        val authMemberId = authentication.getMemberId()
            ?: throw UnauthorizedMemberException("no valid authenticated memberId")

        val authorities = authentication.authorities.joinToString(JWT_AUTHORITY_DELIMITER) { it.authority }

        val nowTime = Date()

        jwtTokenProperties.also {
            val accessToken = generateToken(authMemberId, authorities, nowTime, AuthTokenType.ACCESS)
            val refreshToken = generateToken(authMemberId, authorities, nowTime, AuthTokenType.REFRESH)

            return createAuthTokenRedis(
                memberId = authMemberId,
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
    }

    fun generateToken(
        memberId: Long,
        authorities: String,
        baseDate: Date,
        tokenType: AuthTokenType
    ): String {
        val tokenProperties = tokenType.getTokenConfigProperties(jwtTokenProperties)
        val expirationDate = Date(baseDate.time + tokenProperties.getValidityTimeWithMilliSec())

        return Jwts.builder()
            .setSubject(memberId.toString())
            .claim(JWT_AUTHORITIES_KEY, authorities)
            .setIssuedAt(baseDate)
            .signWith(tokenProperties.getGeneratedKey(), SignatureAlgorithm.HS256)
            .setExpiration(expirationDate)
            .compact()
    }

    private fun createAuthTokenRedis(
        memberId: Long,
        accessToken: String,
        refreshToken: String,
        tokenType: AuthTokenType = AuthTokenType.REFRESH
    ): AuthToken {
        val tokenProperties = tokenType.getTokenConfigProperties(jwtTokenProperties)

        return AuthToken.createEntity(
            memberId = memberId,
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiration = tokenProperties.getValidityTimeWithSec()
        )
    }

    fun getAuthentication(authToken: String, tokenType: AuthTokenType): Authentication? {
        val claims = getValidTokenBody(authToken, tokenType)
            ?: return null

        val memberId = claims.subject

        val authorities = claims[JWT_AUTHORITIES_KEY].toString()
            .split(JWT_AUTHORITY_DELIMITER)
            .map { SimpleGrantedAuthority(it) }

        return UsernamePasswordAuthenticationToken(memberId, authToken, authorities)
    }

    // jwt access token 유효성 검증 및 claims 획득
    private fun getValidTokenBody(authToken: String, tokenType: AuthTokenType): Claims? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(
                    tokenType
                        .getTokenConfigProperties(jwtTokenProperties)
                        .getGeneratedKey()
                )
                .build()
                .parseClaimsJws(authToken)
                .body
        } catch (e: ExpiredJwtException) {
            logger.info { "JWT ${tokenType.name} token expired. > Error: ${e.message}" }
            null
        } catch (e: Exception) {
            logger.info { "JWT ${tokenType.name} token invalid. > Error: ${e.message}" }
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
