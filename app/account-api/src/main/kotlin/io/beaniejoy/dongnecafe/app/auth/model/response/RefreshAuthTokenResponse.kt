package io.beaniejoy.dongnecafe.app.auth.model.response

data class RefreshAuthTokenResponse(
    val accessToken: String
) {
    companion object {
        fun of(accessToken: String): RefreshAuthTokenResponse {
            return RefreshAuthTokenResponse(accessToken)
        }
    }
}
