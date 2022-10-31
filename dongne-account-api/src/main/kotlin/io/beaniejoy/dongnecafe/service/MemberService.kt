package io.beaniejoy.dongnecafe.service

import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.beaniejoy.dongnecafe.domain.member.model.request.MemberRegisterRequest
import io.beaniejoy.dongnecafe.domain.member.repository.MemberRepository
import io.beaniejoy.dongnecafe.error.MemberExistedException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun registerMember(resource: MemberRegisterRequest): Long {
        memberRepository.findByEmail(resource.email!!)?.also {
            throw MemberExistedException(resource.email!!)
        }

        val registeredMember = memberRepository.save(
            Member.createMember(
                email = resource.email!!,
                password = passwordEncoder.encode(resource.password!!),
                address = resource.address!!,
                phoneNumber = resource.phoneNumber!!
            )
        )

        return registeredMember.id
    }
}