package io.beaniejoy.dongnecafe.common.logging

import com.google.gson.Gson
import io.beaniejoy.dongnecafe.common.logging.constant.HttpClientIp
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

fun ContentCachingRequestWrapper.getRequestHeaders(): String? {
    val request = this
    return Gson().toJson(
        mutableMapOf<String, String?>().apply {
            request.headerNames.toList().forEach {
                this@apply[it] = request.getHeader(it)
            }
        }
    )
}

fun ContentCachingRequestWrapper.getRequestParams(): String {
    return this.parameterMap.mapValues {
        it.value.joinToString(",")
    }.entries.joinToString("&")
}

fun ContentCachingRequestWrapper.getClientIp(): String {
    HttpClientIp.values().forEach { clientIpHeader ->
        this.getHeader(clientIpHeader.headerName).also {
            if (it.isNullOrBlank().not() && "unknown".equals(it, true).not()) {
                return it
            }
        }
    }

    return this.remoteAddr
}

fun ContentCachingRequestWrapper.getRequestBody(): String {
    return this.contentAsByteArray.toString(Charsets.UTF_8)
}

// TODO: logging response body maximum size 고려
fun ContentCachingResponseWrapper.getResponseBody(): String {
    return this.contentAsByteArray.toString(Charsets.UTF_8)
}

fun ContentCachingResponseWrapper.getRequestHeaders(): String? {
    val response = this
    return Gson().toJson(
        mutableMapOf<String, String?>().apply {
            response.headerNames.toList().forEach {
                this@apply[it] = response.getHeader(it)
            }
        }
    )
}
