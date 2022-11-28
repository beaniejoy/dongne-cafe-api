package io.beaniejoy.dongnecafe.error.exception

class CafeExistedException(name: String) : RuntimeException("Cafe[$name] is already existed")