package io.beaniejoy.dongnecafe.infra.security

import io.beaniejoy.dongnecafe.domain.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class SecurityUser(
    val member: Member,
    authorities: Collection<GrantedAuthority>
) : User(member.email, member.password, authorities)