package io.beaniejoy.dongnecafe.domain.auth.model

class AuthCommand {
    data class SearchAuthToken(
        val memberId: Long,
        val refreshToken: String
    )
}
