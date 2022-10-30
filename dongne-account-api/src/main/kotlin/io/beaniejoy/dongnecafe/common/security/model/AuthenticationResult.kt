package io.beaniejoy.dongnecafe.common.security.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.security.core.GrantedAuthority

data class AuthenticationResult(
    val email: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val authorities: Collection<GrantedAuthority> = listOf(),
    val msg: String
)