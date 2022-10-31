package io.beaniejoy.dongnecafe.common.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.beaniejoy.dongnecafe.domain.member.model.request.SignInRequest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ApiAuthenticationFilter(requestMatcher: AntPathRequestMatcher) :
    AbstractAuthenticationProcessingFilter(requestMatcher) {

    private val objectMapper = jacksonObjectMapper()

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        if (isValidRequest(request).not()) {
            throw IllegalStateException("request is not supported. check request method and content-type")
        }

        val signInRequest = objectMapper.readValue(request.reader, SignInRequest::class.java)
        request.setAttribute("email", signInRequest.email)

        val token = signInRequest.let {
            if (StringUtils.hasText(it.email).not() || StringUtils.hasText(it.password).not()) {
                throw IllegalArgumentException("Email & Password are not empty!!")
            }

            UsernamePasswordAuthenticationToken(it.email, it.password)
        }

        return authenticationManager.authenticate(token)
    }

    private fun isValidRequest(request: HttpServletRequest): Boolean {
        if (request.method != HttpMethod.POST.name) {
            return false
        }

        if (request.contentType != MediaType.APPLICATION_JSON_VALUE) {
            return false
        }

        return true
    }
}