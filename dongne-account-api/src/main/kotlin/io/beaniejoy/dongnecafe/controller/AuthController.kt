package io.beaniejoy.dongnecafe.controller

import io.beaniejoy.dongnecafe.domain.member.model.request.SignInRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
) {
//    @PostMapping("/authenticate")
//    fun signIn(@RequestBody signInRequest: SignInRequest) {
//        val authenticationToken =
//            UsernamePasswordAuthenticationToken(signInRequest.email, signInRequest.password)
//
//        val authenticate = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
//    }
}