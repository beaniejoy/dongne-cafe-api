package io.beaniejoy.dongnecafe.infra.redis.repository

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import org.springframework.data.repository.CrudRepository

interface AuthTokenRepository : CrudRepository<AuthToken, Long>
