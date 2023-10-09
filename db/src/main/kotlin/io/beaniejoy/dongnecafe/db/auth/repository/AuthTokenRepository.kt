package io.beaniejoy.dongnecafe.db.auth.repository

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface AuthTokenRepository : JpaRepository<AuthToken, Long> {
    fun findByMember(member: Member): AuthToken?
}
