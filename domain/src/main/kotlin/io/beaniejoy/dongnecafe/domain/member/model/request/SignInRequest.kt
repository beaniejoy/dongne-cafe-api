package io.beaniejoy.dongnecafe.domain.member.model.request

data class SignInRequest(
    val email: String,
    val password: String
)