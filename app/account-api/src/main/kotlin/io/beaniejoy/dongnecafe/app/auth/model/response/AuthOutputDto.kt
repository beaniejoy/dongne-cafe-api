package io.beaniejoy.dongnecafe.app.auth.model.response

class AuthOutputDto {
    data class RegisteredAuthTokenResponse(
        val memberId: Long,
        val accessToken: String,
        val refreshToken: String
    )
}
