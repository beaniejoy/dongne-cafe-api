package io.beaniejoy.dongnecafe.domain.auth.entity

import io.beaniejoy.dongnecafe.domain.common.entity.BaseTimeEntity
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.member.entity.Member
import javax.persistence.*

@Entity
@Table(name = "auth_tokens")
class AuthToken protected constructor(
    accessToken: String,
    refreshToken: String
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", nullable = false)
    val id: Long = 0L

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member? = null
        protected set

    @Column(name = "access_token", nullable = false, unique = true)
    var accessToken: String = accessToken
        protected set

    @Column(name = "refresh_token", nullable = false, unique = true)
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
                refreshToken = refreshToken
            ).apply {
                this.member = member
            }
        }
    }

    fun updateTokens(accessToken: String, refreshToken: String) {
        validateTokens(accessToken, refreshToken)

        this.accessToken = accessToken
        this.refreshToken = refreshToken
    }

    private fun validateTokens(accessToken: String, refreshToken: String) {
        if (accessToken == refreshToken) {
            throw BusinessException(
                errorCode = ErrorCode.AUTH_TOKEN_INVALID_REQUEST,
                message = "access, refresh 토큰은 서로 다른 값을 가져야 합니다."
            )
        }
    }
}
