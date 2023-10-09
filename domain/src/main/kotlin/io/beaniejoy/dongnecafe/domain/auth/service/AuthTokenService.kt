package io.beaniejoy.dongnecafe.domain.auth.service

import io.beaniejoy.dongnecafe.domain.auth.model.AuthCommand
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfo
import org.springframework.security.core.Authentication

interface AuthTokenService {
    fun issueNewTokens(authentication: Authentication): AuthInfo.RegisteredAuthToken

    fun refreshToken(command: AuthCommand.RefreshAuthToken)
}
