package io.beaniejoy.dongnecafe.domain.common.utils.security

import io.beaniejoy.dongnecafe.domain.common.utils.security.SecurityConstant.ANONYMOUS_USER
import io.beaniejoy.dongnecafe.domain.common.utils.security.SecurityConstant.ROLE_ANONYMOUS
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.beaniejoy.dongnecafe.domain.member.model.SecurityUser
import org.springframework.security.core.Authentication

fun Authentication.getAuthPrincipal(): String? {
    if (this.isAnonymous()) return null

    return this.principal.toString()
}

private fun Authentication.isAnonymous(): Boolean {
    return this.principal == ANONYMOUS_USER || this.authorities.any { it.authority == ROLE_ANONYMOUS }
}

fun Authentication.getMember(): Member {
    return (this.principal as SecurityUser).member
}
