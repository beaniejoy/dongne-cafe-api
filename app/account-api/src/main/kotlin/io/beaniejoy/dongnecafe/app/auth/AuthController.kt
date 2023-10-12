package io.beaniejoy.dongnecafe.app.auth

import io.beaniejoy.dongnecafe.app.auth.facade.AuthFacade
import io.beaniejoy.dongnecafe.app.auth.model.request.AuthInputDto
import io.beaniejoy.dongnecafe.app.auth.model.request.AuthInputDtoMapper
import io.beaniejoy.dongnecafe.app.auth.model.request.SignInRequest
import io.beaniejoy.dongnecafe.app.auth.model.response.AuthOutputDto
import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authFacade: AuthFacade,
    private val authTokenService: AuthTokenService,
    private val authInputDtoMapper: AuthInputDtoMapper
) {
    @PostMapping("/authenticate")
    fun signIn(
        @RequestBody signInRequest: SignInRequest
    ): ApplicationResponse<AuthOutputDto.RegisteredAuthTokenResponse> {
        val registeredAuthToken = authFacade.signIn(
            email = signInRequest.email,
            password = signInRequest.password
        )

        return ApplicationResponse
            .success("success authenticate")
            .data(registeredAuthToken)
    }

    @GetMapping("/check")
    fun checkAuthenticated(@AuthenticationPrincipal principal: String?): ApplicationResponse<String> {
        return ApplicationResponse
            .success("authenticated")
            .data(principal)
    }

    /**
     * access token만 갱신 대상, refresh token은 검증 용도만
     */
    @PostMapping("/token/refresh")
    fun refreshAccessToken(@RequestBody resource: AuthInputDto.RefreshAuthTokenRequest): ApplicationResponse<String> {
        val refreshCommand = authInputDtoMapper.of(resource)

        val newAccessToken = authTokenService.refreshToken(refreshCommand)

        return ApplicationResponse
            .success()
            .data(newAccessToken)
    }
}
