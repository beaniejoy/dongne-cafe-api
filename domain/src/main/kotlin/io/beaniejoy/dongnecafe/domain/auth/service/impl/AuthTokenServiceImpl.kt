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
import mu.KLogging
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

    companion object : KLogging()

    @Transactional
    override fun issueNewTokens(authentication: Authentication): AuthInfo.RegisteredAuthToken {
        // create or update(when already existed) new tokens(access, refresh)
        val newAuthToken = jwtTokenUtils.createNewAuthToken(authentication)

        // persist(or merge) AuthToken
        val savedAuthToken = authStorePort.store(newAuthToken)

        return authInfoMapper.of(savedAuthToken)
    }

    @Transactional
    override fun renewToken(command: AuthCommand.RenewAuthToken): String {
        authValidator.validateAuthTokens(
            accessToken = command.accessToken,
            refreshToken = command.refreshToken
        )

        // TODO: 상위 Transactional > 하위 Transactional readonly 다른 transactionManager 적용된 경우 어떻게 동작??
        val authToken = getValidAuthTokenEntity(
            memberId = command.memberId,
            accessToken = command.accessToken,
            refreshToken = command.refreshToken
        )

        generateNewAccessToken(authToken.refreshToken).also { newAccessToken ->
            // update new access token of AuthToken
            authToken.updateAccessToken(newAccessToken)
        }

        val savedAuthToken = authStorePort.store(authToken).also {
            logger.info { "saved new access_token: ${it.accessToken}" }
        }

        return savedAuthToken.accessToken
    }

    private fun getValidAuthTokenEntity(
        memberId: Long,
        accessToken: String,
        refreshToken: String
    ): AuthToken {
        return authReaderPort.getAuthTokenByMemberId(memberId)
            ?.also { token ->
                check(token.accessToken == accessToken && token.refreshToken == refreshToken) {
                    throw BusinessException(ErrorCode.AUTH_TOKEN_INVALID_REQUEST, "갱신 토큰이 일치하지 않습니다.")
                }
            }
            ?: throw BusinessException(
                errorCode = ErrorCode.AUTH_TOKEN_INVALID_REQUEST,
                message = "존재하지 않는 인증 토큰입니다."
            )
    }

    // refresh token > generate new access token
    private fun generateNewAccessToken(refreshToken: String): String {
        // refresh token already checked being not expired when validating.
        // so, if authentication in refresh is null, it is not valid token
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
