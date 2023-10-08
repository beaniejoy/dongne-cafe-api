package io.beaniejoy.dongnecafe.common.logging.constant

/**
 * http request client ip possible enum list
 * (ref. https://blog.yevgnenll.me/posts/find-client-ip-from-http-request-header)
 * @property headerName String client ip header name
 */
enum class HttpClientIp(
    val headerName: String,
) {
    X_FORWARDED_FOR("X-Forwarded-For"),
    PROXY_CLIENT_IP("Proxy-Client-IP"),
    WL_PROXY_CLIENT_IP("WL-Proxy-Client-IP"),
    HTTP_X_FORWARDED("HTTP_X_FORWARDED"),
    HTTP_X_FORWARDED_FOR("HTTP_X_FORWARDED_FOR"),
    HTTP_CLIENT_IP("HTTP_CLIENT_IP")
}
