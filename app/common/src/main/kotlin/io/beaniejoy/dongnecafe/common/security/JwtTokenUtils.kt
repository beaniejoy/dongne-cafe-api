package io.beaniejoy.dongnecafe.common.security

import io.beaniejoy.dongnecafe.common.config.JwtTokenProperties
import io.beaniejoy.dongnecafe.common.config.TokenConfigProperties
import io.beaniejoy.dongnecafe.domain.member.model.SecurityUser
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
    companion object : KLogging()

    fun createNewTokens(authentication: Authentication): JwtTokens {
        val nowTime = Date().time

        jwtTokenProperties.also {
            return JwtTokens(
                accessToken = generateToken(authentication, nowTime, it.access),
                refreshToken = generateToken(authentication, nowTime, it.refresh),
            )
        }
    }

    private fun generateToken(
        authentication: Authentication,
        baseTime: Long,
        tokenProperties: TokenConfigProperties
    ): String {
        val authenticatedMember = (authentication.principal as SecurityUser).member
        val authorities = authentication.authorities.joinToString(",") { it.authority }

        val expirationDate = Date(baseTime + tokenProperties.getValidityTimeWithMilliSec())

        return Jwts.builder()
            .setSubject(authenticatedMember.id.toString())
            .claim(JWT_AUTHORITIES_KEY, authorities)
            .signWith(tokenProperties.getGeneratedKey(), SignatureAlgorithm.HS256)
            .setExpiration(expirationDate)
            .compact()
    }

    fun getAuthentication(accessToken: String): Authentication? {
        val claims = getValidTokenBody(accessToken)
            ?: return null

        val authorities = claims[JWT_AUTHORITIES_KEY].toString().split(",")
            .map { SimpleGrantedAuthority(it) }

        return UsernamePasswordAuthenticationToken(claims.subject, accessToken, authorities)
    }

    // jwt access token 유효성 검증 및 claims 획득
    private fun getValidTokenBody(accessToken: String): Claims? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(jwtTokenProperties.access.getGeneratedKey())
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
}

data class JwtTokens(
    val accessToken: String,
    val refreshToken: String
)
