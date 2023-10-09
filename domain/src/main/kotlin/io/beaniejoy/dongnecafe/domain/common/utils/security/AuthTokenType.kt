package io.beaniejoy.dongnecafe.domain.common.utils.security

import io.beaniejoy.dongnecafe.domain.common.config.property.JwtTokenProperties
import io.beaniejoy.dongnecafe.domain.common.config.property.TokenConfigProperties

enum class AuthTokenType {
    ACCESS,
    REFRESH
    ;

    fun getTokenConfigProperties(jwtTokenProperties: JwtTokenProperties): TokenConfigProperties {
        if (this === ACCESS) {
            return jwtTokenProperties.access
        }

        return jwtTokenProperties.refresh
    }
}
