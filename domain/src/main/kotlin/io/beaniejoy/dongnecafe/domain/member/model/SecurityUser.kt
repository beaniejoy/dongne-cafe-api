package io.beaniejoy.dongnecafe.domain.member.model

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class SecurityUser(
    val member: Member,
    authorities: Collection<GrantedAuthority>
) : User(member.email, member.password, authorities) {
    companion object {
        fun of(member: Member): SecurityUser {
            check(member.activated) {
                throw BusinessException(ErrorCode.AUTH_MEMBER_DEACTIVATED)
            }

            return SecurityUser(
                member = member,
                authorities = listOf(SimpleGrantedAuthority(member.roleType.name))
            )
        }
    }
}