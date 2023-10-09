package io.beaniejoy.dongnecafe.domain.auth.service.impl

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfo
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfoMapper
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthTokenReaderPort
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthTokenStorePort
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.domain.common.utils.security.getMember
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthTokenServiceImpl(
    private val jwtTokenUtils: JwtTokenUtils,
    private val authTokenReaderPort: AuthTokenReaderPort,
    private val authTokenStorePort: AuthTokenStorePort,
    private val authInfoMapper: AuthInfoMapper
) : AuthTokenService {

    @Transactional
    override fun issueNewTokens(authentication: Authentication): AuthInfo.RegisteredAuthToken {
        // 기존 저장된 토큰 조회
        val authToken: AuthToken? = authTokenReaderPort.getAuthTokenByMember(authentication.getMember())

        // 토큰 신규 생성(혹은 갱신)
        val newAuthToken = jwtTokenUtils.createOrUpdateNewToken(authToken, authentication)

        val savedAuthToken = authTokenStorePort.store(newAuthToken)

        return authInfoMapper.of(savedAuthToken)
    }
}
