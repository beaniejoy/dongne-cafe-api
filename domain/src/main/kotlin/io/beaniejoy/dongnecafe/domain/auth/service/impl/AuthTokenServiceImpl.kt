package io.beaniejoy.dongnecafe.domain.auth.service.impl

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import io.beaniejoy.dongnecafe.domain.auth.model.AuthCommand
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfo
import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfoMapper
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthReaderPort
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthRemoverPort
import io.beaniejoy.dongnecafe.domain.auth.persistence.AuthStorePort
import io.beaniejoy.dongnecafe.domain.auth.service.AuthTokenService
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import mu.KLogging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthTokenServiceImpl(
    private val jwtTokenUtils: JwtTokenUtils,
    private val authInfoMapper: AuthInfoMapper,
    private val authReaderPort: AuthReaderPort,
    private val authStorePort: AuthStorePort,
    private val authRemoverPort: AuthRemoverPort
) : AuthTokenService {

    companion object : KLogging()

    override fun issueNewTokens(authentication: Authentication): AuthInfo.RegisteredAuthToken {
        // create or update(when already existed) new tokens(access, refresh)
        val newAuthToken = jwtTokenUtils.createNewAuthToken(authentication)

        // persist(or merge) AuthToken
        val savedAuthToken = authStorePort.store(newAuthToken)

        return authInfoMapper.of(savedAuthToken)
    }

    override fun renewToken(command: AuthCommand.SearchAuthToken): AuthInfo.RegisteredAuthToken {
        val authToken = getValidAuthTokenEntity(
            memberId = command.memberId,
            refreshToken = command.refreshToken
        )

        val savedAuthToken = generateNewAuthToken(authToken.refreshToken).run {
            authStorePort.store(this)
        }

        return authInfoMapper.of(savedAuthToken)
    }

    override fun removeToken(logoutMemberId: Long, command: AuthCommand.SearchAuthToken) {
        if (logoutMemberId != command.memberId) {
            throw BusinessException(ErrorCode.AUTH_TOKEN_INVALID_REQUEST)
        }

        val authToken = getValidAuthTokenEntity(
            memberId = logoutMemberId,
            refreshToken = command.refreshToken
        )

        authRemoverPort.delete(authToken)
    }

    private fun getValidAuthTokenEntity(
        memberId: Long,
        refreshToken: String?
    ): AuthToken {
        return authReaderPort.getAuthTokenByMemberId(memberId)
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

    // generate new access token from refresh token
    private fun generateNewAuthToken(refreshToken: String): AuthToken {
        // refresh token already checked being not expired when validating.
        // so, if authentication in refresh is null, it is not valid token
        val refreshAuth = jwtTokenUtils.getAuthentication(refreshToken, AuthTokenType.REFRESH)
            ?: throw BusinessException(
                errorCode = ErrorCode.AUTH_TOKEN_INVALID_REQUEST,
                message = "유효하지 않는 ${AuthTokenType.REFRESH}입니다."
            )

        return jwtTokenUtils.createNewAuthToken(refreshAuth)
    }
}
