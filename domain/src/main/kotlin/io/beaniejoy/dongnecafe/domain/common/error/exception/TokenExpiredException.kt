package io.beaniejoy.dongnecafe.domain.common.error.exception

import org.springframework.security.core.AuthenticationException

class TokenExpiredException(msg: String? = null) : AuthenticationException(msg)
