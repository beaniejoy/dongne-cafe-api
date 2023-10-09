package io.beaniejoy.dongnecafe.domain.auth.service.validator

import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthReaderPort
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import org.springframework.stereotype.Component

@Component
class AuthValidatorImpl(
    private val jwtTokenUtils: JwtTokenUtils,
    private val authReaderPort: AuthReaderPort
) : AuthValidator {
    // TODO: 여기서부터 다시 진행(access, refresh 검증 부분)
    override fun validateAuthToken(accessToken: String, refreshToken: String) {
        jwtTokenUtils.checkTokenExpired(accessToken, AuthTokenType.ACCESS)
        jwtTokenUtils.checkTokenExpired(refreshToken, AuthTokenType.REFRESH)

        val authToken = authReaderPort.getAuthTokenByAccessToken(accessToken)
    }
}
