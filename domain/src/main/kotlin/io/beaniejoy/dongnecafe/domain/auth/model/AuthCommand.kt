package io.beaniejoy.dongnecafe.domain.auth.model

class AuthCommand {
    data class RefreshAuthToken(
        val accessToken: String,
        val refreshToken: String
    )
}
