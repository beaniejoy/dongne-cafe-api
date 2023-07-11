package io.beaniejoy.dongnecafe.domain.member.persistence

import io.beaniejoy.dongnecafe.domain.member.entity.Member

interface MemberReaderPort {
    fun existsMemberByEmail(email: String): Boolean

    fun getMemberByEmail(email: String): Member?
}