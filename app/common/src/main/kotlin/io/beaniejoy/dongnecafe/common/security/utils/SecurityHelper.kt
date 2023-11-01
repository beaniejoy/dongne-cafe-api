package io.beaniejoy.dongnecafe.common.security.utils

import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import org.springframework.http.HttpHeaders.AUTHORIZATION
import java.net.URLDecoder
import javax.servlet.http.HttpServletRequest

class SecurityHelper {

    companion object {
        private const val WHITESPACE = " "

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

            val splitBearer = tokenValue.split(WHITESPACE)
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
                ?.filter { it.name == AUTHORIZATION }
                ?.also { authorizations ->
                    // Invalid, if one more authorization values exists in cookie,
                    if (authorizations.size > 1) {
                        throw BusinessException(ErrorCode.AUTH_TOKEN_INVALID_REQUEST)
                    }
                }
                ?.firstOrNull()
                ?.value
                ?.let {
                    // decoding cookie value with UTF-8 charset
                    URLDecoder.decode(it, Charsets.UTF_8)
                }

            // if both values are null, auth token is not existed
            if (headerValue == null && cookieValue == null) {
                return null
            }

            return headerValue ?: cookieValue
        }

        // ex) Refresh [auth_token]
        fun getAuthTokenValue(tokenType: AuthTokenType, authToken: String): String {
            return "${tokenType.getCapitalizedPrefix()}${WHITESPACE}$authToken"
        }
    }
}
