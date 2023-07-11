package io.beaniejoy.dongnecafe.app.auth

import io.beaniejoy.dongnecafe.app.auth.facade.AuthFacade
import io.beaniejoy.dongnecafe.app.auth.model.request.SignInRequest
import io.beaniejoy.dongnecafe.app.auth.model.response.TokenResponse
import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import mu.KLogging
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authFacade: AuthFacade
) {
    companion object: KLogging()

    @PostMapping("/authenticate")
    fun signIn(@RequestBody signInRequest: SignInRequest): ApplicationResponse<TokenResponse> {
        val accessToken = authFacade.signIn(
            email = signInRequest.email,
            password = signInRequest.password
        )

        return ApplicationResponse
            .success("success authenticate")
            .data(TokenResponse(accessToken))
    }

    @GetMapping("/check")
    fun checkAuthenticated(@AuthenticationPrincipal principal: String?): ApplicationResponse<String> {
        logger.info { "[Authentication Principal] $principal" }

        return ApplicationResponse
            .success("authenticated")
            .data(principal)
    }
}