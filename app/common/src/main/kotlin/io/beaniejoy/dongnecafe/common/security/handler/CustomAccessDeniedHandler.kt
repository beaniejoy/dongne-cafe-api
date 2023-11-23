package io.beaniejoy.dongnecafe.common.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KLogging
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver

@Component
class CustomAccessDeniedHandler(
    private val handlerExceptionResolver: HandlerExceptionResolver
) : AccessDeniedHandler {
    companion object : KLogging()

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        logger.info { "Access Denied!!!!!" }
        handlerExceptionResolver.resolveException(request, response, null, accessDeniedException)
    }
}
