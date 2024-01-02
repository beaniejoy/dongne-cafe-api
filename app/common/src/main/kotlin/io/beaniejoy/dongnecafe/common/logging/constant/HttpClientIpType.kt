package io.beaniejoy.dongnecafe.common.logging.constant

import jakarta.servlet.http.HttpServletRequest

/**
 * http request client ip possible enum list
 * (ref. https://blog.yevgnenll.me/posts/find-client-ip-from-http-request-header)
 * 우선 필요한 것들만 정의(추후 필요한 부분 있으면 추가하기로,,)
 * @property headerName String client ip header name
 * @property priority check header names ordered by priority number
 */
enum class HttpClientIpType(
    val headerName: String,
    private val priority: Int = 0
) {
    X_FORWARDED_FOR("X-Forwarded-For"),
    X_REAL_IP("X-Real-IP", Int.MAX_VALUE)
    ;

    companion object {
        private const val UNKNOWN_CLIENT = "unknown"
        private val SORTED_PRIORITY_CLIENT_IP_TYPES = values().sortedByDescending { it.priority }

        // 우선순위에 따라 순차적으로 header value 값이 있는지 조회
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
