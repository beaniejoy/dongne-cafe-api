package io.beaniejoy.dongnecafe.domain.member.service.impl

import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.beaniejoy.dongnecafe.domain.member.model.MemberCommand
import io.beaniejoy.dongnecafe.domain.member.model.MemberInfo
import io.beaniejoy.dongnecafe.domain.member.model.MemberInfoMapper
import io.beaniejoy.dongnecafe.domain.member.persistence.MemberReaderPort
import io.beaniejoy.dongnecafe.domain.member.persistence.MemberStorePort
import io.beaniejoy.dongnecafe.domain.member.service.MemberService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberReaderPort: MemberReaderPort,
    private val memberStorePort: MemberStorePort,
    private val passwordEncoder: PasswordEncoder,
    private val memberInfoMapper: MemberInfoMapper
) : MemberService {

    @Transactional
    override fun registerMember(command: MemberCommand.RegisterMember): MemberInfo.RegisteredMember {
        check(memberReaderPort.existsMemberByEmail(command.email).not()) {
            throw BusinessException(ErrorCode.MEMBER_EXISTED)
        }

        val savedMember = memberStorePort.store(
            Member.createEntity(
                command = command,
                passwordEncoder = passwordEncoder
            )
        )

        return memberInfoMapper.of(savedMember)
    }
}
