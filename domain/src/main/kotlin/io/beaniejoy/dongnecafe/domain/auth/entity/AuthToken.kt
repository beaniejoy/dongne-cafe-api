package io.beaniejoy.dongnecafe.domain.auth.entity

import io.beaniejoy.dongnecafe.domain.common.entity.BaseTimeEntity
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import javax.persistence.Id

@RedisHash("auth_tokens")
class AuthToken protected constructor(
    id: Long,
    accessToken: String,
    refreshToken: String,
    expiration: Long
) : BaseTimeEntity() {

    @Id
    var id: Long = id
        protected set

    var accessToken: String = accessToken
        protected set

    var refreshToken: String = refreshToken
        protected set

    @TimeToLive
    var expiration: Long = expiration
        protected set

    init {
        validateTokens(accessToken, refreshToken)
    }

    companion object {
        // JWT 토큰 설정된 것보다 여유 있게 캐시 설정
        private const val EXTRA_EXPIRATION_SEC = 5

        fun createEntity(
            memberId: Long,
            accessToken: String,
            refreshToken: String,
            expiration: Long
        ): AuthToken {
            return AuthToken(
                id = memberId,
                accessToken = accessToken,
                refreshToken = refreshToken,
                expiration = expiration + EXTRA_EXPIRATION_SEC
            )
        }
    }

    fun updateTokens(accessToken: String) {
        validateTokens(accessToken, this.refreshToken)

        this.accessToken = accessToken
    }

    private fun validateTokens(accessToken: String, refreshToken: String) {
        check(accessToken != refreshToken) {
            throw BusinessException(
                errorCode = ErrorCode.AUTH_TOKEN_INVALID_REQUEST,
                message = "access, refresh 토큰은 서로 다른 값을 가져야 합니다."
            )
        }
    }
}
