package io.beaniejoy.dongnecafe.service

import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.beaniejoy.dongnecafe.domain.member.repository.MemberRepository
import io.beaniejoy.dongnecafe.error.MemberDeactivatedException
import mu.KLogging
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component("userDetailsService")
class UserDetailsServiceImpl(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    companion object: KLogging()

    override fun loadUserByUsername(email: String): UserDetails {
        return memberRepository.findByEmail(email)?.let {
            logger.info { "[LOAD MEMBER] email: ${it.email}, role: ${it.roleType}, activated: ${it.activated}" }
            createSecurityUser(it)
        } ?: throw UsernameNotFoundException(email)
    }

    private fun createSecurityUser(member: Member): User {
        if (member.activated.not()) {
            throw MemberDeactivatedException(member.email)
        }

        return User(
            /* username = */ member.email,
            /* password = */ member.password,
            /* authorities = */ listOf(SimpleGrantedAuthority(member.roleType.name))
        )
    }
}