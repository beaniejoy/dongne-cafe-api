package io.beaniejoy.dongnecafe.app.auth.facade

import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfo
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    authenticationConfiguration: AuthenticationConfiguration,
    private val authTokenService: AuthTokenService,
) {
    private lateinit var authenticationManager: AuthenticationManager

    init {
        authenticationManager = authenticationConfiguration.authenticationManager
    }

    fun signIn(email: String, password: String): AuthInfo.RegisteredAuthToken {
        // 인증 진행
        val authenticationToken = UsernamePasswordAuthenticationToken(email, password)
        val authentication = authenticationManager.authenticate(authenticationToken)

        // 신규 토큰 발행
        return authTokenService.issueNewTokens(authentication)
    }
}
