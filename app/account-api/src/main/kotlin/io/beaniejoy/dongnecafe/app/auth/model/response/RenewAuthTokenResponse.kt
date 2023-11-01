package io.beaniejoy.dongnecafe.app.auth.model.response

data class RenewAuthTokenResponse(
    val accessToken: String
) {
    companion object {
        fun of(accessToken: String): RenewAuthTokenResponse {
            return RenewAuthTokenResponse(accessToken)
        }
    }
}
