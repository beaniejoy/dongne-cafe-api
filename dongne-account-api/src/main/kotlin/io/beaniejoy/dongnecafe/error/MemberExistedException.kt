package io.beaniejoy.dongnecafe.error

class MemberExistedException(email: String): RuntimeException("Member[$email] is already existed")