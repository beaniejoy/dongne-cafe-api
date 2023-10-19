package io.beaniejoy.dongnecafe.infra.redis.adapter

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthReaderPort
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import io.beaniejoy.dongnecafe.infra.redis.repository.AuthTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class AuthTokenReaderAdapter(
    private val authTokenRepository: AuthTokenRepository
) : AuthReaderPort {
    override fun getAuthTokenByMember(member: Member): AuthToken? {
        return authTokenRepository.findByIdOrNull(member.id)
    }

    override fun getAuthTokenByAccessToken(accessToken: String): AuthToken? {
        return authTokenRepository.findByAccessToken(accessToken)
    }
}
