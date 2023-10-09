package io.beaniejoy.dongnecafe.domain.auth.persistence

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken

interface AuthTokenStorePort {
    fun store(authToken: AuthToken): AuthToken
}
