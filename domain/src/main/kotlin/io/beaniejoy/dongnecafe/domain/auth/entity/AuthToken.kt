package io.beaniejoy.dongnecafe.domain.auth.entity

import io.beaniejoy.dongnecafe.domain.common.entity.BaseTimeEntity
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@RedisHash("auth_tokens")
class AuthToken protected constructor(
    id: Long,
    accessToken: String,
    refreshToken: String
) : BaseTimeEntity() {

    // TODO: 불필요한 key 값이 생김 (하나의 auth_token에 대해서 2개의 key가 생기는 셈)
    // @Id 없애도 되는지도 볼 것, @Id하고 필드이름하고 똑같아야 하는 것인지도 찾아볼 것
    @Id
    var id: Long = id
        protected set

    @Indexed
    var accessToken: String = accessToken
        protected set

    var refreshToken: String = refreshToken
        protected set

    init {
        validateTokens(accessToken, refreshToken)
    }

    companion object {
        fun createEntity(
            member: Member,
            accessToken: String,
            refreshToken: String
        ): AuthToken {
            return AuthToken(
                accessToken = accessToken,
                refreshToken = refreshToken,
                id = member.id
            )
        }
    }

    fun updateTokens(accessToken: String, refreshToken: String) {
        validateTokens(accessToken, refreshToken)

        this.accessToken = accessToken
        this.refreshToken = refreshToken
    }

    fun updateAccessToken(accessToken: String) {
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
