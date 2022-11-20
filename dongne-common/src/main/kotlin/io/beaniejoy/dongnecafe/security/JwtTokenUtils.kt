package io.beaniejoy.dongnecafe.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtTokenUtils(
    @Value("\${jwt.secret_key}")
    private val secretKey: String,
    @Value("\${jwt.expiration_time}")
    private val expirationTime: Long
) {
    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    companion object: KLogging()

    fun createToken(authentication: Authentication): String {
        val authenticatedMember = (authentication.principal as SecurityUser).member
        val nowTime = Date().time
        val expirationDate = Date(nowTime + this.expirationTime)

        return Jwts.builder()
            .setSubject(authenticatedMember.email)
            .claim("memberId", authenticatedMember.id)
            .claim("email", authenticatedMember.email)
            .claim("roles", authentication.authorities.joinToString(",") { it.authority })
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expirationDate)
            .compact()
    }
}