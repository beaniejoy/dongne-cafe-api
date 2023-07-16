package io.beaniejoy.dongnecafe.domain.member.service.impl

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.member.model.SecurityUser
import io.beaniejoy.dongnecafe.domain.member.persistence.MemberReaderPort
import mu.KLogging
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(
    private val memberReaderPort: MemberReaderPort
) : UserDetailsService {
    companion object : KLogging()

    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String): SecurityUser {
        return memberReaderPort.getMemberByEmail(email)?.let {
            logger.info { "[LOAD MEMBER] email: ${it.email}, role: ${it.roleType}, activated: ${it.activated}" }
            SecurityUser.of(it)
        } ?: throw UsernameNotFoundException(ErrorCode.AUTH_MEMBER_NOT_FOUND.name)
    }
}