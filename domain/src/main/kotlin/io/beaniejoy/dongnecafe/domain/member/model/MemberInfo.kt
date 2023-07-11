package io.beaniejoy.dongnecafe.domain.member.model

class MemberInfo {
    data class RegisteredMember(
        val memberId: Long,
        val email: String
    )
}