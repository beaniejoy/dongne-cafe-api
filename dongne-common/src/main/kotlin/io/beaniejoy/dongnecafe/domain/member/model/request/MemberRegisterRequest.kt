package io.beaniejoy.dongnecafe.domain.member.model.request

class MemberRegisterRequest(
    val email: String? = null,
    val password: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null
)