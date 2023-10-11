package io.beaniejoy.dongnecafe.domain.auth.service.impl

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.model.AuthCommand
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfo
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfoMapper
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthReaderPort
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthStorePort
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import io.beaniejoy.dongnecafe.domain.auth.service.validator.AuthValidator
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.domain.common.utils.security.SecurityConstant.JWT_AUTHORITY_DELIMITER
import io.beaniejoy.dongnecafe.domain.common.utils.security.getAuthPrincipal
import io.beaniejoy.dongnecafe.domain.common.utils.security.getMember
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

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
    override fun refreshToken(command: AuthCommand.RefreshAuthToken): String {
        authValidator.validateAuthTokens(
            accessToken = command.accessToken,
            refreshToken = command.refreshToken
        )

        // TODO: 상위 Transactional > 하위 Transactional readonly 다른 transactionManager 적용된 경우 어떻게 동작??
        val authToken = getValidAuthTokenEntity(
            accessToken = command.accessToken,
            refreshToken = command.refreshToken
        )

        return generateNewAccessToken(authToken.refreshToken).also {
            authToken.updateAccessToken(it)
        }
    }

    private fun getValidAuthTokenEntity(accessToken: String, refreshToken: String): AuthToken {
        return authReaderPort.getAuthTokenByAccessToken(accessToken)
            ?.also { token ->
                check(token.refreshToken == refreshToken) {
                    throw BusinessException(ErrorCode.AUTH_TOKEN_INVALID_REQUEST, "갱신 토큰이 일치하지 않습니다.")
                }
            }
            ?: throw BusinessException(
                errorCode = ErrorCode.AUTH_TOKEN_INVALID_REQUEST,
                message = "존재하지 않는 인증 토큰입니다."
            )
    }

    private fun generateNewAccessToken(refreshToken: String): String {
        // refresh token already checked being not expired when validating.
        // so, if authentication in refresh is null, it is not valid
        val refreshAuth = jwtTokenUtils.getAuthentication(refreshToken, AuthTokenType.REFRESH)
            ?: throw BusinessException(
                errorCode = ErrorCode.AUTH_TOKEN_INVALID_REQUEST,
                message = "유효하지 않는 ${AuthTokenType.REFRESH}입니다."
            )

        val memberId = refreshAuth.getAuthPrincipal()?.toLong()
            ?: throw BusinessException(ErrorCode.AUTH_TOKEN_INVALID_REQUEST)

        val authorities = refreshAuth.authorities
            .joinToString(JWT_AUTHORITY_DELIMITER) { it.authority }

        val nowTime = Date()

        return jwtTokenUtils.generateToken(
            authenticatedMemberId = memberId,
            authorities = authorities,
            baseDate = nowTime,
            tokenType = AuthTokenType.ACCESS
        )
    }
}
