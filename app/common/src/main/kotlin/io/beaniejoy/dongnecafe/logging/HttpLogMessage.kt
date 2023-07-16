package io.beaniejoy.dongnecafe.logging

import org.springframework.http.HttpStatus
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

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
                clientIp = requestWrapper.getClientIp(),
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
        |>> CLIENT_IP: ${this.clientIp}
        |>> HEADERS: ${this.headers}
        |>> REQUEST_PARAM: ${this.requestParam}
        |>> REQUEST_BODY: ${this.requestBody}
        |>> RESPONSE_BODY: ${this.responseBody}
        """.trimMargin()
    }
}