package io.beaniejoy.dongnecafe.infra

import io.beaniejoy.dongnecafe.utils.logging.getRequestBody
import io.beaniejoy.dongnecafe.utils.logging.getRequestHeaders
import io.beaniejoy.dongnecafe.utils.logging.getRequestParams
import io.beaniejoy.dongnecafe.utils.logging.getResponseBody
import org.springframework.http.HttpStatus
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

// TODO: clientIP에 대해서 고민해보기
data class HttpLogMessage(
    val httpMethod: String,
    val requestUri: String,
    val httpStatus: HttpStatus,
    val clientIp: String,
    val elapsedTime: Double,
    val headers: String?,
    val requestParam: String?,
    val requestBody: String?,
    val responseBody: String?,
) {
    companion object {
        fun createInstance(
            requestWrapper: ContentCachingRequestWrapper,
            responseWrapper: ContentCachingResponseWrapper,
            elapsedTime: Double
        ): HttpLogMessage {
            return HttpLogMessage(
                httpMethod = requestWrapper.method,
                requestUri = requestWrapper.requestURI,
                httpStatus = HttpStatus.valueOf(responseWrapper.status),
                clientIp = requestWrapper.remoteAddr,
                elapsedTime = elapsedTime,
                headers = requestWrapper.getRequestHeaders(),
                requestParam = requestWrapper.getRequestParams(),
                requestBody = requestWrapper.getRequestBody(),
                responseBody = responseWrapper.getResponseBody(),
            )
        }
    }

    fun toPrettierLog(): String {
        return """
        |
        |[REQUEST] ${this.httpMethod} ${this.requestUri} ${this.httpStatus} (${this.elapsedTime})
        |CLIENT_IP: ${this.clientIp}
        |HEADERS: ${this.headers}
        |REQUEST_PARAM: ${this.requestParam}
        |REQUEST_BODY: ${this.requestBody}
        |RESPONSE_BODY: ${this.responseBody}
        """.trimMargin()
    }
}