package io.beaniejoy.dongnecafe.app.auth.model.response

import io.beaniejoy.dongnecafe.common.security.JwtTokens

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(jwtTokens: JwtTokens): TokenResponse {
            return TokenResponse(
                accessToken = jwtTokens.accessToken,
                refreshToken = jwtTokens.refreshToken
            )
        }
    }
}
