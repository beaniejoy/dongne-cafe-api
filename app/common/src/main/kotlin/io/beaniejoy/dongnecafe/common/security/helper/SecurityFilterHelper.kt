package io.beaniejoy.dongnecafe.common.security.helper

import io.beaniejoy.dongnecafe.domain.common.utils.security.SecurityConstant
import org.springframework.http.HttpHeaders
import javax.servlet.http.HttpServletRequest

class SecurityFilterHelper {
    companion object {
        /**
         * 인증 토큰 획득
         * Authorization : Bearer [AUTH_TOKEN]
         */
        fun getAccessToken(request: HttpServletRequest): String? {
            val bearer = request.getHeader(HttpHeaders.AUTHORIZATION)
                ?: return null

            val splitBearer = bearer.split(SecurityConstant.WHITESPACE)
            if (splitBearer.first() != SecurityConstant.BEARER) {
                return null
            }

            if (splitBearer.size != 2 || splitBearer.last().isBlank()) {
                return null
            }

            return splitBearer.last()
        }
    }
}
