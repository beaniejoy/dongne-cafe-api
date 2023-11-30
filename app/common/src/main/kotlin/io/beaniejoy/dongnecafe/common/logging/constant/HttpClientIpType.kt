package io.beaniejoy.dongnecafe.common.logging.constant

import jakarta.servlet.http.HttpServletRequest

/**
 * http request client ip possible enum list
 * (ref. https://blog.yevgnenll.me/posts/find-client-ip-from-http-request-header)
 * @property headerName String client ip header name
 * @property priority check header names ordered by priority number
 */
enum class HttpClientIpType(
    val headerName: String,
    private val priority: Int = 0
) {
    X_FORWARDED_FOR("X-Forwarded-For"),
    PROXY_CLIENT_IP("Proxy-Client-IP"),
    WL_PROXY_CLIENT_IP("WL-Proxy-Client-IP"),
    HTTP_X_FORWARDED("HTTP_X_FORWARDED"),
    HTTP_X_FORWARDED_FOR("HTTP_X_FORWARDED_FOR"),
    HTTP_CLIENT_IP("HTTP_CLIENT_IP"),
    X_REAL_IP("X-Real-IP", Int.MAX_VALUE)
    ;

    companion object {
        private const val UNKNOWN_CLIENT = "unknown"
        private val SORTED_PRIORITY_CLIENT_IP_TYPES = values().sortedByDescending { it.priority }

        fun findClientIpInHeader(request: HttpServletRequest): String {
            SORTED_PRIORITY_CLIENT_IP_TYPES.forEach { httpClientIp ->
                request.getHeader(httpClientIp.headerName).also { headerValue ->
                    if (isValidClientIp(headerValue)) {
                        return headerValue
                    }
                }
            }

            return request.remoteAddr ?: UNKNOWN_CLIENT
        }

        private fun isValidClientIp(headerValue: String?): Boolean {
            return headerValue.isNullOrBlank().not() &&
                UNKNOWN_CLIENT.equals(headerValue, true).not()
        }
    }
}
