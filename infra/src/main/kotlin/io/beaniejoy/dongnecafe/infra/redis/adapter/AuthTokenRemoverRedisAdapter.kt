package io.beaniejoy.dongnecafe.infra.redis.adapter

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthRemoverPort
import io.beaniejoy.dongnecafe.infra.redis.repository.AuthTokenRepository
import org.springframework.stereotype.Component

@Component
class AuthTokenRemoverRedisAdapter(
    private val authTokenRepository: AuthTokenRepository
) : AuthRemoverPort {
    override fun delete(authToken: AuthToken) {
        authTokenRepository.delete(authToken)
    }
}
