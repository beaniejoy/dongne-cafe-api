package io.beaniejoy.dongnecafe.infra.redis.repository

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import org.springframework.data.jpa.repository.JpaRepository

interface AuthTokenRepository : JpaRepository<AuthToken, Long> {
    fun findByAccessToken(accessToken: String): AuthToken?
}
