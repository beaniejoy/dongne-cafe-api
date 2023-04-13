package io.beaniejoy.dongnecafe.infra

import com.google.gson.Gson
import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// TODO: 리팩토링 필요(logback-spring.xml 같이)
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

        // TODO: GET에 대해서 request body가 안찍힘
        log.info { """
            |
            |[REQUEST] ${request.method} ${request.requestURI} ${response.status} (${(end - startTime) / 1000.0})
            |HEADERS: ${getRequestHeaders(request)}
            |REQUEST_PARAM: ${getRequestParams(request)}
            |REQUEST_BODY: ${getRequestBody(cachingRequestWrapper)}
            |RESPONSE_BODY: ${getResponseBody(cachingResponseWrapper)}
        """.trimMargin() }

        cachingResponseWrapper.copyBodyToResponse()

        MDC.remove(REQUEST_ID)
    }

    private fun getRequestHeaders(request: HttpServletRequest): String {
        return Gson().toJson(
            mutableMapOf<String, String?>().apply {
                request.headerNames.toList().forEach { this[it] = request.getHeader(it) }
            }
        )
    }

    private fun getRequestParams(request: HttpServletRequest): String {
        return request.parameterMap.mapValues {
            it.value.joinToString(",")
        }.entries.joinToString("&")
    }

    private fun getRequestBody(requestWrapper: ContentCachingRequestWrapper): String {
        return requestWrapper.contentAsByteArray.toString(Charsets.UTF_8)
    }

    // TODO: logging response body maximum size 고려
    private fun getResponseBody(responseWrapper: ContentCachingResponseWrapper): String {
        return responseWrapper.contentAsByteArray.toString(Charsets.UTF_8)
    }
}