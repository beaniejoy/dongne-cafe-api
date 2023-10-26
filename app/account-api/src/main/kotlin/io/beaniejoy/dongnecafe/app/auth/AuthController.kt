package io.beaniejoy.dongnecafe.app.auth

import io.beaniejoy.dongnecafe.app.auth.facade.AuthFacade
import io.beaniejoy.dongnecafe.app.auth.model.request.AuthInputDto
import io.beaniejoy.dongnecafe.app.auth.model.request.AuthInputDtoMapper
import io.beaniejoy.dongnecafe.app.auth.model.request.SignInRequest
import io.beaniejoy.dongnecafe.app.auth.model.response.AuthOutputDto
import io.beaniejoy.dongnecafe.app.auth.model.response.AuthOutputDtoMapper
import io.beaniejoy.dongnecafe.app.auth.model.response.RenewAuthTokenResponse
import io.beaniejoy.dongnecafe.app.common.annotation.RenewToken
import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.common.response.utils.addSafeCookie
import io.beaniejoy.dongnecafe.common.security.utils.SecurityHelper
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import org.springframework.http.HttpHeaders
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authFacade: AuthFacade,
    private val authTokenService: AuthTokenService,
    private val authInputDtoMapper: AuthInputDtoMapper,
    private val authOutputDtoMapper: AuthOutputDtoMapper
) {
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

    @GetMapping("/check")
    fun checkAuthenticated(@AuthenticationPrincipal memberId: String): ApplicationResponse<String> {
        return ApplicationResponse
            .success("authenticated")
            .data(memberId)
    }

    /**
     * access token, refresh token 갱신
     * (기존 refresh token으로 갱신)
     */
    @PostMapping("/token/renew")
    fun renewAccessToken(
        @RenewToken resource: AuthInputDto.RenewAuthTokenRequest,
        response: HttpServletResponse
    ): ApplicationResponse<RenewAuthTokenResponse> {
        val renewCommand = authInputDtoMapper.of(resource)

        val updatedAuthToken = authTokenService.renewToken(renewCommand)

        addRefreshTokenCookie(
            response = response,
            refreshToken = updatedAuthToken.refreshToken
        )

        return ApplicationResponse
            .success()
            .data(RenewAuthTokenResponse.of(updatedAuthToken.accessToken))
    }

    private fun addRefreshTokenCookie(response: HttpServletResponse, refreshToken: String) {
        response.addSafeCookie(
            name = HttpHeaders.AUTHORIZATION,
            value = SecurityHelper.getAuthTokenValue(AuthTokenType.REFRESH, refreshToken)
        )
    }
}
