package io.beaniejoy.dongnecafe.controller

import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.domain.member.model.request.SignInRequest
import io.beaniejoy.dongnecafe.model.TokenResponse
import io.beaniejoy.dongnecafe.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val jwtTokenUtils: JwtTokenUtils
) {
    @PostMapping("/authenticate")
    fun signIn(@RequestBody signInRequest: SignInRequest): ApplicationResponse {
        val authentication = authService.signIn(
            email = signInRequest.email,
            password = signInRequest.password
        )

        val accessToken = jwtTokenUtils.createToken(authentication)

        return ApplicationResponse
            .success("success authenticate")
            .data(TokenResponse(accessToken))
    }
}