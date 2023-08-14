package io.beaniejoy.dongnecafe.utils.security

import io.beaniejoy.dongnecafe.utils.security.SecurityConstant.ANONYMOUS_USER
import io.beaniejoy.dongnecafe.utils.security.SecurityConstant.ROLE_ANONYMOUS
import org.springframework.security.core.Authentication

fun Authentication.getAuthPrincipal(): String? {
    if (this.isAnonymous()) return null

    return this.principal.toString()
}

fun Authentication.isAnonymous(): Boolean {
    return this.principal == ANONYMOUS_USER || this.authorities.any { it.authority == ROLE_ANONYMOUS }
}
