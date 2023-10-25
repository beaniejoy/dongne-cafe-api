package io.beaniejoy.dongnecafe.app.auth.model.request

class AuthInputDto {
    data class RenewAuthTokenRequest(
        val memberId: Long,
        val refreshToken: String
    )
}
