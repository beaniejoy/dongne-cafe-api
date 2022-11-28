package io.beaniejoy.dongnecafe.common.security

import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.beaniejoy.dongnecafe.domain.member.repository.MemberRepository
import io.beaniejoy.dongnecafe.error.MemberDeactivatedException
import io.beaniejoy.dongnecafe.security.SecurityUser
import mu.KLogging
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component("userDetailsService")
class UserDetailsServiceImpl(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    companion object: KLogging()

    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String): SecurityUser {
        return memberRepository.findByEmail(email)?.let {
            logger.info { "[LOAD MEMBER] email: ${it.email}, role: ${it.roleType}, activated: ${it.activated}" }
            createSecurityUser(it)
        } ?: throw UsernameNotFoundException("${email} is not found")
    }

    private fun createSecurityUser(member: Member): SecurityUser {
        if (member.activated.not()) {
            throw MemberDeactivatedException(member.email)
        }

        return SecurityUser(
            member = member,
            authorities = listOf(SimpleGrantedAuthority(member.roleType.name))
        )
    }
}