package io.beaniejoy.dongnecafe.app.auth.facade

import io.beaniejoy.dongnecafe.security.JwtTokenUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    authenticationConfiguration: AuthenticationConfiguration,
    private val jwtTokenUtils: JwtTokenUtils
) {
    private val authenticationManager: AuthenticationManager = authenticationConfiguration.authenticationManager

    fun signIn(email: String, password: String): String {
        val authenticationToken = UsernamePasswordAuthenticationToken(email, password)

        val authentication = authenticationManager.authenticate(authenticationToken)

        return jwtTokenUtils.createToken(authentication)
    }
}