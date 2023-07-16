package io.beaniejoy.dongnecafe.app.auth.model.request

data class SignInRequest(
    val email: String,
    val password: String
)