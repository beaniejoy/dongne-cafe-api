package io.beaniejoy.dongnecafe.domain.cafe.error

class CafeExistedException(name: String) : RuntimeException("Cafe[$name] is already existed")