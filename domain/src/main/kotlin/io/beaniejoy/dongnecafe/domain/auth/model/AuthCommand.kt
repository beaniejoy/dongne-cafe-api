package io.beaniejoy.dongnecafe.domain.auth.model

class AuthCommand {
    data class RenewAuthToken(
        val memberId: Long,
        val refreshToken: String
    )
}
