package io.beaniejoy.dongnecafe.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthService(authenticationConfiguration: AuthenticationConfiguration) {
    private val authenticationManager: AuthenticationManager = authenticationConfiguration.authenticationManager

    fun signIn(email: String, password: String): Authentication {
        val authenticationToken = UsernamePasswordAuthenticationToken(email, password)

        return authenticationManager.authenticate(authenticationToken)
    }
}