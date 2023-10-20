package io.beaniejoy.dongnecafe.infra.redis.adapter

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthStorePort
import io.beaniejoy.dongnecafe.infra.redis.repository.AuthTokenRepository
import org.springframework.stereotype.Component

@Component
class AuthTokenStoreRedisAdapter(
    private val authTokenRepository: AuthTokenRepository
) : AuthStorePort {
    override fun store(authToken: AuthToken): AuthToken {
        return authTokenRepository.save(authToken)
    }
}
