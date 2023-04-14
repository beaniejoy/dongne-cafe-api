package io.beaniejoy.dongnecafe.utils.logging

import com.google.gson.Gson
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.http.HttpServletRequest

fun HttpServletRequest.getRequestHeaders(): String? {
    val request = this
    return Gson().toJson(
        mutableMapOf<String, String?>().apply {
            request.headerNames.toList().forEach {
                this[it] = request.getHeader(it)
            }
        }
    )
}

fun HttpServletRequest.getRequestParams(): String {
    return this.parameterMap.mapValues {
        it.value.joinToString(",")
    }.entries.joinToString("&")
}

fun ContentCachingRequestWrapper.getRequestBody(): String {
    return this.contentAsByteArray.toString(Charsets.UTF_8)
}

// TODO: logging response body maximum size 고려
fun ContentCachingResponseWrapper.getResponseBody(): String {
    return this.contentAsByteArray.toString(Charsets.UTF_8)
}