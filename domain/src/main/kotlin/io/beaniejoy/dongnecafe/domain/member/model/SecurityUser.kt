package io.beaniejoy.dongnecafe.domain.member.model

import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class SecurityUser(
    val member: Member,
    authorities: Collection<GrantedAuthority>
) : User(member.email, member.password, authorities) {
    init {
        check(member.isActivated()) {
            throw BusinessException(ErrorCode.AUTH_MEMBER_DEACTIVATED)
        }
    }

    companion object {
        fun of(member: Member): SecurityUser {
            return SecurityUser(
                member = member,
                authorities = listOf(SimpleGrantedAuthority(member.roleType.name))
            )
        }
    }
}
