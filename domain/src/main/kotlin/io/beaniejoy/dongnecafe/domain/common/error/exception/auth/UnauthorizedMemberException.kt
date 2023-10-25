package io.beaniejoy.dongnecafe.domain.common.error.exception.auth

import org.springframework.security.core.AuthenticationException

class UnauthorizedMemberException(msg: String? = null) : AuthenticationException(msg)
