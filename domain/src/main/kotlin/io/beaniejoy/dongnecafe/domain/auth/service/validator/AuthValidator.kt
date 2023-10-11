package io.beaniejoy.dongnecafe.domain.auth.service.validator

interface AuthValidator {
    fun validateAuthTokens(accessToken: String, refreshToken: String)
}
