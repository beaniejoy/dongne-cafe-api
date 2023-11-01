package io.beaniejoy.dongnecafe.app.auth

import io.beaniejoy.dongnecafe.app.auth.facade.AuthFacade
import io.beaniejoy.dongnecafe.app.auth.model.request.AuthInputDto
import io.beaniejoy.dongnecafe.app.auth.model.request.AuthInputDtoMapper
import io.beaniejoy.dongnecafe.app.auth.model.request.SignInRequest
import io.beaniejoy.dongnecafe.app.auth.model.response.AuthOutputDto
import io.beaniejoy.dongnecafe.app.auth.model.response.AuthOutputDtoMapper
import io.beaniejoy.dongnecafe.app.auth.model.response.RenewAuthTokenResponse
import io.beaniejoy.dongnecafe.app.common.annotation.RenewTokenCookie
import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.common.response.utils.addSafeCookie
import io.beaniejoy.dongnecafe.common.response.utils.deleteCookie
import io.beaniejoy.dongnecafe.common.security.utils.SecurityHelper
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import org.springframework.http.HttpHeaders
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authFacade: AuthFacade,
    private val authTokenService: AuthTokenService,
    private val authInputDtoMapper: AuthInputDtoMapper,
    private val authOutputDtoMapper: AuthOutputDtoMapper
) {
    companion object {
        const val AUTHORIZATION_HEADER = HttpHeaders.AUTHORIZATION
    }

    @PostMapping("/authenticate")
    fun signIn(
        @RequestBody signInRequest: SignInRequest,
        response: HttpServletResponse
    ): ApplicationResponse<AuthOutputDto.RegisteredAuthTokenResponse> {
        val registeredAuthToken = authFacade.signIn(
            email = signInRequest.email,
            password = signInRequest.password
        )

        addRefreshTokenCookie(
            response = response,
            refreshToken = registeredAuthToken.refreshToken
        )

        return ApplicationResponse
            .success("success authenticate")
            .data(authOutputDtoMapper.of(registeredAuthToken))
    }

    /**
     * access token, refresh token 갱신
     * (cookie refresh token 으로 갱신)
     */
    @PostMapping("/token/renew")
    fun renewAccessToken(
        @RenewTokenCookie resource: AuthInputDto.SearchAuthTokenRequest,
        response: HttpServletResponse
    ): ApplicationResponse<RenewAuthTokenResponse> {
        val searchTokenCommand = authInputDtoMapper.of(resource)

        val updatedAuthToken = authTokenService.renewToken(searchTokenCommand)

        addRefreshTokenCookie(
            response = response,
            refreshToken = updatedAuthToken.refreshToken
        )

        return ApplicationResponse
            .success()
            .data(RenewAuthTokenResponse.of(updatedAuthToken.accessToken))
    }

    /**
     * logout
     * Authorization Header(access), Cookie(refresh) required
     */
    @PostMapping("/logout")
    fun logout(
        @AuthenticationPrincipal memberId: String,
        @RenewTokenCookie resource: AuthInputDto.SearchAuthTokenRequest,
        response: HttpServletResponse
    ): ApplicationResponse<String> {
        val searchTokenCommand = authInputDtoMapper.of(resource)

        authTokenService.removeToken(
            logoutMemberId = memberId.toLong(),
            command = searchTokenCommand
        )

        response.deleteCookie(AUTHORIZATION_HEADER)

        return ApplicationResponse
            .success("logout")
            .data(memberId)
    }

    private fun addRefreshTokenCookie(response: HttpServletResponse, refreshToken: String) {
        response.addSafeCookie(
            name = AUTHORIZATION_HEADER,
            value = SecurityHelper.getAuthTokenValue(AuthTokenType.REFRESH, refreshToken)
        )
    }
}
