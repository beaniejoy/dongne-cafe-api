package io.beaniejoy.dongnecafe.domain.auth.service.validator

import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.common.error.exception.auth.UserAuthExpiredException
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import org.springframework.stereotype.Component

@Component
class AuthValidatorImpl(private val jwtTokenUtils: JwtTokenUtils) : AuthValidator {

    // accessToken: 만료 / refreshToken: 유효 >> 해당 케이스만 통과
    override fun validateAuthTokens(accessToken: String, refreshToken: String) {
        // 각 토큰들에 대한 만료 체크
        val isAccessTokenExpired = jwtTokenUtils.checkTokenExpired(
            authToken = accessToken,
            tokenType = AuthTokenType.ACCESS
        )
        val isRefreshTokenExpired = jwtTokenUtils.checkTokenExpired(
            authToken = refreshToken,
            tokenType = AuthTokenType.REFRESH
        )

        // 둘 다 만료된 경우 인증 오류
        if (isAccessTokenExpired && isRefreshTokenExpired) {
            throw UserAuthExpiredException()
        }

        // access 만료된 케이스만 통과
        check(isAccessTokenExpired) {
            // access 유효 or 올바르지 못한 토큰
            throw BusinessException(ErrorCode.AUTH_TOKEN_INVALID_REQUEST, "인증 토큰이 아직 유효합니다.")
        }
    }
}
