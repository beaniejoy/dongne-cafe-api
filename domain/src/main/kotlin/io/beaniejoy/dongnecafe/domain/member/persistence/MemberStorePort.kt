package io.beaniejoy.dongnecafe.domain.member.persistence

import io.beaniejoy.dongnecafe.domain.member.entity.Member

interface MemberStorePort {
    fun store(member: Member): Member
}