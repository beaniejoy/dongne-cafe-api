package io.beaniejoy.dongnecafe.db.member.adapter

import io.beaniejoy.dongnecafe.db.member.repository.MemberRepository
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.beaniejoy.dongnecafe.domain.member.persistence.MemberStorePort
import org.springframework.stereotype.Component

@Component
class MemberStoreAdapter(
    private val memberRepository: MemberRepository
) : MemberStorePort {
    override fun store(member: Member): Member {
        return memberRepository.save(member)
    }
}