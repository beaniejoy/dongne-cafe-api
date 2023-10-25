package io.beaniejoy.dongnecafe.common.security.helper

import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import io.beaniejoy.dongnecafe.domain.common.utils.security.SecurityConstant
import org.springframework.http.HttpHeaders.AUTHORIZATION
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

class SecurityHelper {
    companion object {
        /**
         * 인증 토큰 획득(header or cookie)
         * Authorization : Bearer [ACCESS_TOKEN] / Refresh [REFRESH_TOKEN]
         * >> 현재 스펙에서는 header, cookie 각각 한 개의 Authorization만 가능하다.
         */
        fun getAuthTokenFromRequest(
            request: HttpServletRequest,
            tokenType: AuthTokenType,
            isOnlyCookie: Boolean = false
        ): String? {
            val tokenValue = extractValueFromRequest(request, isOnlyCookie)
                ?: return null

            val splitBearer = tokenValue.split(SecurityConstant.WHITESPACE)
            if (splitBearer.first() != tokenType.getCapitalizedPrefix()) {
                return null
            }

            if (splitBearer.size != 2 || splitBearer.last().isBlank()) {
                return null
            }

            return splitBearer.last()
        }

        private fun extractValueFromRequest(request: HttpServletRequest, isOnlyCookie: Boolean): String? {
            val headerValue = if (isOnlyCookie.not()) request.getHeader(AUTHORIZATION) else null

            val cookieValue = request.cookies
                .filter { it.name == AUTHORIZATION }
                .also { authorizations ->
                    // Invalid, if one more authorization values exists in cookie,
                    if (authorizations.size > 1) {
                        throw BusinessException(ErrorCode.AUTH_TOKEN_INVALID_REQUEST)
                    }
                }
                .firstOrNull()
                ?.value

            // if both values are null, auth token is not existed
            if (headerValue == null && cookieValue == null) {
                return null
            }

            return headerValue ?: cookieValue
        }

        fun generateRefreshTokenCookie(refreshToken: String, domain: String): Cookie {
            return Cookie(
                AUTHORIZATION,
                "${AuthTokenType.REFRESH.getCapitalizedPrefix()}${SecurityConstant.WHITESPACE}$refreshToken"
            ).apply {
                this.domain = domain
                this.secure = true
                this.isHttpOnly = true
            }
        }
    }
}
