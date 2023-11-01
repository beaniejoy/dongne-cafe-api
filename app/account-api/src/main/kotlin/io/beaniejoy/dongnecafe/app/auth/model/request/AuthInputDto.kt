package io.beaniejoy.dongnecafe.app.auth.model.request

class AuthInputDto {
    data class SearchAuthTokenRequest(
        val memberId: Long,
        val refreshToken: String
    )
}
