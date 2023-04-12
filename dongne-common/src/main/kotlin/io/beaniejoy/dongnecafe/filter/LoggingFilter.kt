package io.beaniejoy.dongnecafe.filter

import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// TODO: 리팩토링 필요(logback-spring.xml 같이)
@Component
@Order(1)
class LoggingFilter: OncePerRequestFilter() {
    private val log = KotlinLogging.logger {}

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val requestId = UUID.randomUUID().toString().substring(0, 8)
        MDC.put("request_id", requestId)

        log.info{ "request_id = $requestId" }
        filterChain.doFilter(request, response)

        MDC.remove("request_id")
    }
}