package io.beaniejoy.dongnecafe.common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
data class JwtTokenProperties(
    val access: TokenConfigProperties,
    val refresh: TokenConfigProperties
)
