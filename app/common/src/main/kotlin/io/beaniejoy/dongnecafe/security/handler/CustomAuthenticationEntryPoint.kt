package io.beaniejoy.dongnecafe.security.handler

import mu.KLogging
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint(
    private val handlerExceptionResolver: HandlerExceptionResolver
) : AuthenticationEntryPoint {
    companion object : KLogging()

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        logger.info { "Unauthorized Error!!" }
        handlerExceptionResolver.resolveException(request, response, null, authException)
    }
}
