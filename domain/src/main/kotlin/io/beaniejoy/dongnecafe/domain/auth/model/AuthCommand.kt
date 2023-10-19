package io.beaniejoy.dongnecafe.domain.auth.model

class AuthCommand {
    data class RenewAuthToken(
        val memberId: Long,
        val accessToken: String,
        val refreshToken: String
    )
}
