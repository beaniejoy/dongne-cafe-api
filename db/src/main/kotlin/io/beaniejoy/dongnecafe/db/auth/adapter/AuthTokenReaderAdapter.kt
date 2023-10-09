package io.beaniejoy.dongnecafe.db.auth.adapter

import io.beaniejoy.dongnecafe.db.auth.repository.AuthTokenRepository
import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthReaderPort
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class AuthTokenReaderAdapter(
    private val authTokenRepository: AuthTokenRepository
) : AuthReaderPort {
    override fun getAuthTokenByMember(member: Member): AuthToken? {
        return authTokenRepository.findByMember(member)
    }

    override fun getAuthTokenByAccessToken(accessToken: String): AuthToken? {
        return authTokenRepository.findByAccessToken(accessToken)
    }
}
