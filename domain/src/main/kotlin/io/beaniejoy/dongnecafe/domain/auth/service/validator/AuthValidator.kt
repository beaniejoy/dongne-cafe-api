package io.beaniejoy.dongnecafe.domain.auth.service.validator

interface AuthValidator {
    fun validateAuthToken(accessToken: String, refreshToken: String)
}
