package io.beaniejoy.dongnecafe.security.handler

import mu.KLogging
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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
