package io.beaniejoy.dongnecafe.app.auth.model.request

class AuthInputDto {
    data class RefreshAuthTokenRequest(
        val accessToken: String,
        val refreshToken: String
    )
}
