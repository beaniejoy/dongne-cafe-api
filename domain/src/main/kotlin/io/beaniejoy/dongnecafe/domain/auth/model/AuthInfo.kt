package io.beaniejoy.dongnecafe.domain.auth.model

class AuthInfo {

    data class RegisteredAuthToken(
        val memberId: Long,
        val email: String,
        val accessToken: String,
        val refreshToken: String
    )
}
