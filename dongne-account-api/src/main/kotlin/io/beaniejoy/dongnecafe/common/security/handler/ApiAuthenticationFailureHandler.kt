package io.beaniejoy.dongnecafe.common.security.handler

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.beaniejoy.dongnecafe.common.security.model.AuthenticationResult
import io.beaniejoy.dongnecafe.domain.member.model.request.SignInRequest
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ApiAuthenticationFailureHandler : AuthenticationFailureHandler {
    private val objectMapper = jacksonObjectMapper()

    companion object : KLogging()

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException,
    ) {
        val email = request.getAttribute("email") as String
        logger.error { "[AUTH FAILED] $email" }

        response.apply {
            this.status = HttpStatus.UNAUTHORIZED.value()
            this.contentType = MediaType.APPLICATION_JSON_VALUE
        }

        objectMapper.writeValue(
            response.writer,
            AuthenticationResult(
                email = email,
                msg = "authentication failed"
            )
        )
    }
}