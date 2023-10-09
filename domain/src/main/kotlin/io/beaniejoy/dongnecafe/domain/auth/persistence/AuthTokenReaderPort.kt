package io.beaniejoy.dongnecafe.domain.auth.persistence

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.member.entity.Member

interface AuthTokenReaderPort {
    fun getAuthTokenByMember(member: Member): AuthToken?
}
