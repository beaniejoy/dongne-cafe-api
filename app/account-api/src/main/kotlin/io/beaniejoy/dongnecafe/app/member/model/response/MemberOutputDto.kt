package io.beaniejoy.dongnecafe.app.member.model.response

class MemberOutputDto {
    data class RegisteredMemberResponse(
        val memberId: Long,
        val email: String
    )
}
