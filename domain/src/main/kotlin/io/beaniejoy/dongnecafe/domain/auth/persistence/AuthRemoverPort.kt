package io.beaniejoy.dongnecafe.domain.auth.persistence

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken

interface AuthRemoverPort {
    fun delete(authToken: AuthToken)
}
