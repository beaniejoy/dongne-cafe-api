package io.beaniejoy.dongnecafe.domain.member.repository

import io.beaniejoy.dongnecafe.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}