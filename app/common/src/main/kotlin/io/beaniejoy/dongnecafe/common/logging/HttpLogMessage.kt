package io.beaniejoy.dongnecafe.common.logging

import org.springframework.http.HttpStatus
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import org.yaml.snakeyaml.util.UriEncoder

data class HttpLogMessage(
    val httpMethod: String,
    val requestUri: String,
    val httpStatus: HttpStatus,
    val clientIp: String,
    val elapsedTime: Double,
    val requestHeaders: String?,
    val requestParam: String?,
    val requestBody: String?,
    val responseHeaders: String?,
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
                requestHeaders = requestWrapper.getRequestHeaders(),
                requestParam = requestWrapper.getRequestParams(),
                requestBody = requestWrapper.getRequestBody(),
                responseHeaders = responseWrapper.getRequestHeaders(),
                responseBody = responseWrapper.getResponseBody(),
            )
        }
    }

    fun toPrettierLog(): String {
        return """
        |
        |[REQUEST] ${this.httpMethod} ${UriEncoder.decode(this.requestUri)} ${this.httpStatus} (${this.elapsedTime}s)
        |>> CLIENT_IP: ${this.clientIp}
        |>> (in)REQUEST_HEADERS: ${this.requestHeaders}
        |>> (in)REQUEST_PARAM: ${this.requestParam}
        |>> (in)REQUEST_BODY: ${this.requestBody}
        |>> (out)RESPONSE_HEADERS: ${this.responseHeaders}
        |>> (out)RESPONSE_BODY: ${this.responseBody}
        """.trimMargin()
    }
}
