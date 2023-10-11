package io.beaniejoy.dongnecafe.domain.common.error.exception.auth

import org.springframework.security.core.AuthenticationException

// access token 만 만료시
class TokenExpiredException(msg: String? = null) : AuthenticationException(msg)
