package io.beaniejoy.dongnecafe.db.member.adapter

import io.beaniejoy.dongnecafe.db.member.repository.MemberRepository
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.beaniejoy.dongnecafe.domain.member.persistence.MemberReaderPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class MemberReaderAdapter(private val memberRepository: MemberRepository) : MemberReaderPort {
    override fun existsMemberByEmail(email: String): Boolean {
        return memberRepository.findByEmail(email) != null
    }

    override fun getMemberByEmail(email: String): Member? {
        return memberRepository.findByEmail(email)
    }

    override fun getMemberByIdNotNull(memberId: Long): Member {
        return memberRepository.findByIdOrNull(memberId)
            ?: throw BusinessException(ErrorCode.MEMBER_NOT_FOUND)
    }
}
