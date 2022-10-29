package io.beaniejoy.dongnecafe.common.security

import mu.KLogging
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

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
        TODO("Not yet implemented")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}