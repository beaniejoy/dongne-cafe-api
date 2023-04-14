package io.beaniejoy.dongnecafe.infra

import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class RequestResponseLoggingFilter : OncePerRequestFilter() {
    private val log = KotlinLogging.logger {}

    companion object {
        const val REQUEST_ID = "request_id"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val cachingRequestWrapper = ContentCachingRequestWrapper(request)
        val cachingResponseWrapper = ContentCachingResponseWrapper(response)

        val requestId = UUID.randomUUID().toString().substring(0, 8)

        MDC.put(REQUEST_ID, requestId)

        val startTime = System.currentTimeMillis()
        filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper)
        val end = System.currentTimeMillis()

        try {
            log.info {
                HttpLogMessage.createInstance(
                    requestWrapper = cachingRequestWrapper,
                    responseWrapper = cachingResponseWrapper,
                    elapsedTime = (end - startTime) / 1000.0
                ).toPrettierLog()
            }

            cachingResponseWrapper.copyBodyToResponse()
        } catch (e: Exception) {
            log.error(e) { "[RequestResponseLoggingFilter] logging 실패" }
        } finally {
            MDC.remove(REQUEST_ID)
        }
    }
}