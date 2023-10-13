package io.beaniejoy.dongnecafe.domain.auth.persistence

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.member.entity.Member

interface AuthReaderPort {
    fun getAuthTokenByMember(member: Member): AuthToken?

    fun getAuthTokenByAccessToken(accessToken: String): AuthToken?
}
