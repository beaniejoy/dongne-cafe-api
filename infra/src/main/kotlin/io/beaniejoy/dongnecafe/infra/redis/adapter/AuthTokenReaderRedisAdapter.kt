package io.beaniejoy.dongnecafe.infra.redis.adapter

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthReaderPort
import io.beaniejoy.dongnecafe.infra.redis.repository.AuthTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AuthTokenReaderRedisAdapter(
    private val authTokenRepository: AuthTokenRepository
) : AuthReaderPort {
    override fun getAuthTokenByMemberId(memberId: Long): AuthToken? {
        return authTokenRepository.findByIdOrNull(memberId)
    }
}
