package io.beaniejoy.dongnecafe.security.utils

import io.beaniejoy.dongnecafe.security.constant.SecurityConstant.ANONYMOUS_USER
import io.beaniejoy.dongnecafe.security.constant.SecurityConstant.ROLE_ANONYMOUS
import org.springframework.security.core.Authentication

fun Authentication.getAuthPrincipal() : String? {
    if (this.isAnonymous()) return null

    return this.principal.toString()
}

fun Authentication.isAnonymous(): Boolean {
    return this.principal == ANONYMOUS_USER || this.authorities.any { it.authority == ROLE_ANONYMOUS }
}