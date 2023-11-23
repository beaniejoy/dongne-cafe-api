package io.beaniejoy.dongnecafe.domain.common.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtTokenProperties(
    val access: TokenConfigProperties,
    val refresh: TokenConfigProperties
)
