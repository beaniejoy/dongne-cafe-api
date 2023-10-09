package io.beaniejoy.dongnecafe.domain.auth.service.impl

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.model.AuthCommand
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfo
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfoMapper
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthReaderPort
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthStorePort
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import io.beaniejoy.dongnecafe.domain.auth.service.validator.AuthValidator
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.domain.common.utils.security.getMember
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthTokenServiceImpl(
    private val jwtTokenUtils: JwtTokenUtils,
    private val authReaderPort: AuthReaderPort,
    private val authStorePort: AuthStorePort,
    private val authInfoMapper: AuthInfoMapper,
    private val authValidator: AuthValidator
) : AuthTokenService {

    @Transactional
    override fun issueNewTokens(authentication: Authentication): AuthInfo.RegisteredAuthToken {
        // 기존 저장된 토큰 조회
        val authToken: AuthToken? = authReaderPort.getAuthTokenByMember(authentication.getMember())

        // 토큰 신규 생성(혹은 갱신)
        val newAuthToken = jwtTokenUtils.createOrUpdateNewToken(authToken, authentication)

        val savedAuthToken = authStorePort.store(newAuthToken)

        return authInfoMapper.of(savedAuthToken)
    }

    @Transactional
    override fun refreshToken(command: AuthCommand.RefreshAuthToken) {
        authValidator.validateAuthToken(
            accessToken = command.accessToken,
            refreshToken = command.refreshToken
        )
    }
}
