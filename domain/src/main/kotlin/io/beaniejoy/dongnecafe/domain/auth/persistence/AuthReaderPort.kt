package io.beaniejoy.dongnecafe.domain.auth.persistence

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken

interface AuthReaderPort {
    fun getAuthTokenByMemberId(memberId: Long): AuthToken?
}
