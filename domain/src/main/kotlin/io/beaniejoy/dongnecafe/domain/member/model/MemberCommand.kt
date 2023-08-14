package io.beaniejoy.dongnecafe.domain.member.model

class MemberCommand {
    data class RegisterMember(
        val email: String,
        val password: String,
        val address: String,
        val phoneNumber: String
    )
}
