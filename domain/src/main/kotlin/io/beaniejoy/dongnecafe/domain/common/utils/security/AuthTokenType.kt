package io.beaniejoy.dongnecafe.domain.common.utils.security

import io.beaniejoy.dongnecafe.domain.common.config.property.JwtTokenProperties
import io.beaniejoy.dongnecafe.domain.common.config.property.TokenConfigProperties

enum class AuthTokenType(
    val typeName: String
) {
    ACCESS("인증토큰"),
    REFRESH("갱신토큰")
    ;

    fun getTokenConfigProperties(jwtTokenProperties: JwtTokenProperties): TokenConfigProperties {
        if (this === ACCESS) {
            return jwtTokenProperties.access
        }

        return jwtTokenProperties.refresh
    }
}
