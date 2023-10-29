package io.beaniejoy.dongnecafe.domain.auth.service

import io.beaniejoy.dongnecafe.domain.auth.model.AuthCommand
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfo
import org.springframework.security.core.Authentication

interface AuthTokenService {
    /**
     * persist new AuthToken Entity or update
     * @param authentication Authentication authenticated object
     * @return AuthInfo.RegisteredAuthToken
     */
    fun issueNewTokens(authentication: Authentication): AuthInfo.RegisteredAuthToken

    /**
     * refresh only access token (not refresh token) and update AuthToken Entity
     * @param command AuthCommand.RenewAuthToken > accessToken(expired), refreshToken(valid)
     * @return String > accessToken(new token)
     */
    fun renewToken(command: AuthCommand.SearchAuthToken): AuthInfo.RegisteredAuthToken

    fun removeToken(logoutMemberId: Long, command: AuthCommand.SearchAuthToken)
}
