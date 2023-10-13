package io.beaniejoy.dongnecafe.db.auth.adapter

import io.beaniejoy.dongnecafe.db.auth.repository.AuthTokenRepository
import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthStorePort
import org.springframework.stereotype.Component

@Component
class AuthTokenStoreAdapter(
    private val authTokenRepository: AuthTokenRepository
) : AuthStorePort {
    override fun store(authToken: AuthToken): AuthToken {
        return authTokenRepository.save(authToken)
    }
}
