package io.beaniejoy.dongnecafe.app.auth.facade

import io.beaniejoy.dongnecafe.common.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.common.security.JwtTokens
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    authenticationConfiguration: AuthenticationConfiguration,
    private val jwtTokenUtils: JwtTokenUtils
) {
    private val authenticationManager: AuthenticationManager = authenticationConfiguration.authenticationManager

    fun signIn(email: String, password: String): JwtTokens {
        val authenticationToken = UsernamePasswordAuthenticationToken(email, password)

        val authentication = authenticationManager.authenticate(authenticationToken)

        return jwtTokenUtils.createNewTokens(authentication)
    }
}
