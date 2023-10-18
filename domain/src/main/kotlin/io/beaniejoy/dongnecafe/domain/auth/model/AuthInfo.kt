package io.beaniejoy.dongnecafe.domain.auth.model

class AuthInfo {

    data class RegisteredAuthToken(
        val memberId: Long,
        val accessToken: String,
        val refreshToken: String
    )
}
