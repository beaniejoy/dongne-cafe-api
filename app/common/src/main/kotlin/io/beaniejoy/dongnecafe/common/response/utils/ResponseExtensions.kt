package io.beaniejoy.dongnecafe.common.response.utils

import java.net.URLEncoder
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

private const val DEFAULT_COOKIE_PATH = "/"

// add httpOnly, secure cookie for preventing from XSS, CSRF attacking
fun HttpServletResponse.addSafeCookie(name: String, value: String) {
    this.addCookie(
        Cookie(name, URLEncoder.encode(value, Charsets.UTF_8)).apply {
            // TODO maxAge 설정 필요
            this.secure = true
            this.isHttpOnly = true
            this.path = DEFAULT_COOKIE_PATH
        }
    )
}

fun HttpServletResponse.deleteCookie(name: String) {
    this.addCookie(
        Cookie(name, null).apply {
            this.maxAge = 0
            this.path = DEFAULT_COOKIE_PATH
        }
    )
}
