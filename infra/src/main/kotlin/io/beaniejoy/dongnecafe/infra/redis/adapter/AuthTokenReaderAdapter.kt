package io.beaniejoy.dongnecafe.infra.redis.adapter

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthReaderPort
import io.beaniejoy.dongnecafe.infra.redis.repository.AuthTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class AuthTokenReaderAdapter(
    private val authTokenRepository: AuthTokenRepository
) : AuthReaderPort {
    override fun getAuthTokenByMemberId(memberId: Long): AuthToken? {
        return authTokenRepository.findByIdOrNull(memberId)
    }
}
