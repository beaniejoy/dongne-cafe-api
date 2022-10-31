package io.beaniejoy.dongnecafe.error

class MemberDeactivatedException(email: String): RuntimeException("Member[$email] is deactivated")