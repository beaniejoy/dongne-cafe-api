package io.beaniejoy.dongnecafe.app.auth.facade

import io.beaniejoy.dongnecafe.app.auth.model.response.AuthOutputDto
import io.beaniejoy.dongnecafe.app.auth.model.response.AuthOutputDtoMapper
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    authenticationConfiguration: AuthenticationConfiguration,
    private val authTokenService: AuthTokenService,
    private val authOutputDtoMapper: AuthOutputDtoMapper
) {
    private lateinit var authenticationManager: AuthenticationManager

    init {
        authenticationManager = authenticationConfiguration.authenticationManager
    }

    fun signIn(email: String, password: String): AuthOutputDto.RegisteredAuthTokenResponse {
        // 인증 진행
        val authenticationToken = UsernamePasswordAuthenticationToken(email, password)
        val authentication = authenticationManager.authenticate(authenticationToken)

        // 신규 토큰 발행
        val newAuthToken = authTokenService.issueNewTokens(authentication)

        return authOutputDtoMapper.of(newAuthToken)
    }
}
