package io.beaniejoy.dongnecafe.app.member.model.request

class MemberInputDto {
    data class RegisterMemberRequest(
        val email: String? = null,
        val password: String? = null,
        val address: String? = null,
        val phoneNumber: String? = null
    )
}