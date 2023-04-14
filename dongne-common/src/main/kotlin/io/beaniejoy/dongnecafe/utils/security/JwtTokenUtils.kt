package io.beaniejoy.dongnecafe.utils.security

import io.beaniejoy.dongnecafe.security.SecurityUser
import io.beaniejoy.dongnecafe.security.constant.SecurityConstant.JWT_AUTHORITIES_KEY
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtTokenUtils(
    @Value("\${jwt.secret_key}")
    private val secretKey: String,
    @Value("\${jwt.validity_time_in_sec}")
    private val validityTimeSec: Long
) {
    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())
    private val validityTimeMilliSec: Long = validityTimeSec * 1000

    companion object : KLogging()

    fun createToken(authentication: Authentication): String {
        val authenticatedMember = (authentication.principal as SecurityUser).member
        val authorities = authentication.authorities.joinToString(",") { it.authority }

        val nowTime = Date().time
        val expirationDate = Date(nowTime + this.validityTimeMilliSec)

        return Jwts.builder()
            .setSubject(authenticatedMember.email)
            .claim(JWT_AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS256)
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
                .setSigningKey(key)
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