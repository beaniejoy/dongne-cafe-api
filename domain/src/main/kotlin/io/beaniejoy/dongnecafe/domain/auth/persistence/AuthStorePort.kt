package io.beaniejoy.dongnecafe.domain.auth.persistence

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken

interface AuthStorePort {
    fun store(authToken: AuthToken): AuthToken
}
