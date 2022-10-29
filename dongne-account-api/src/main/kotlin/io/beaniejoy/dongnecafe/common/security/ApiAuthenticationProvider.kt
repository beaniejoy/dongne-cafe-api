package io.beaniejoy.dongnecafe.common.security

import mu.KLogging
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * 실제 인증 절차 수행
 * @property userDetailsService email로 계정 찾기
 */
@Component
class ApiAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {
    companion object: KLogging()

    override fun authenticate(authentication: Authentication): Authentication {
        logger.info { "start authentication" }

        val email = authentication.name
        val password = authentication.credentials as String?

        val user = userDetailsService.loadUserByUsername(email)
        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("Input password does not match stored password")
        }

        // password null로 반환
        return UsernamePasswordAuthenticationToken(email, null, user.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}