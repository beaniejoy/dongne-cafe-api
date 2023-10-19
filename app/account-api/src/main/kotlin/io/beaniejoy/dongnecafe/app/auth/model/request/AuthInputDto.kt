package io.beaniejoy.dongnecafe.app.auth.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class AuthInputDto {
    data class RenewAuthTokenRequest(
        @field:NotNull(message = "사용자 ID는 필수 입력값 입니다.")
        val memberId: Long? = null,
        @field:NotBlank(message = "인증 토큰은 필수 입력값 입니다.")
        val accessToken: String? = null,
        @field:NotBlank(message = "갱신 토큰은 필수 입력값 입니다.")
        val refreshToken: String? = null
    )
}
