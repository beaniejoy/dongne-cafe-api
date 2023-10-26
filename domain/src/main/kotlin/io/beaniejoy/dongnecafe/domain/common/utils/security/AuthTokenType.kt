package io.beaniejoy.dongnecafe.domain.common.utils.security

import io.beaniejoy.dongnecafe.domain.common.config.property.JwtTokenProperties
import io.beaniejoy.dongnecafe.domain.common.config.property.TokenConfigProperties
import java.util.*

enum class AuthTokenType(
    private val prefix: HeaderPrefix
) {
    ACCESS(HeaderPrefix.BEARER),
    REFRESH(HeaderPrefix.REFRESH)
    ;

    fun getTokenConfigProperties(jwtTokenProperties: JwtTokenProperties): TokenConfigProperties {
        if (this === ACCESS) {
            return jwtTokenProperties.access
        }

        return jwtTokenProperties.refresh
    }

    fun getCapitalizedPrefix(): String {
        return prefix.capitalize()
    }

    enum class HeaderPrefix {
        BEARER,
        REFRESH
        ;

        fun capitalize(): String {
            return this.name.lowercase()
                .replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
        }
    }
}
