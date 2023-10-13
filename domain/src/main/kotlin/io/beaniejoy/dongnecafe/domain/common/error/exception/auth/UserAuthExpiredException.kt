package io.beaniejoy.dongnecafe.domain.common.error.exception.auth

import org.springframework.security.core.AuthenticationException

// 사용자가 아예 인증 만료된 경우(access, refersh 둘 다 만료)
class UserAuthExpiredException(msg: String? = null) : AuthenticationException(msg)
