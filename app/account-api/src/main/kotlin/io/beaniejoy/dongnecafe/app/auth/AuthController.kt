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
import io.beaniejoy.dongnecafe.common.security.helper.SecurityHelper
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
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

    companion object {
        const val APP_DOMAIN_NAME = "localhost"
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

        response.apply {
            this.addCookie(
                SecurityHelper.generateRefreshTokenCookie(
                    refreshToken = registeredAuthToken.refreshToken,
                    domain = APP_DOMAIN_NAME
                )
            )
        }

        return ApplicationResponse
            .success("success authenticate")
            .data(authOutputDtoMapper.of(registeredAuthToken))
    }

    @GetMapping("/check")
    fun checkAuthenticated(@AuthenticationPrincipal principal: String?): ApplicationResponse<String> {
        return ApplicationResponse
            .success("authenticated")
            .data(principal)
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

        response.apply {
            this.addCookie(
                SecurityHelper.generateRefreshTokenCookie(
                    refreshToken = updatedAuthToken.refreshToken,
                    domain = APP_DOMAIN_NAME
                )
            )
        }

        return ApplicationResponse
            .success()
            .data(RenewAuthTokenResponse.of(updatedAuthToken.accessToken))
    }
}
