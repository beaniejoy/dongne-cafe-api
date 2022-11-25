package io.beaniejoy.dongnecafe.common.error.exception

class CafeExistedException(name: String) : RuntimeException("Cafe[$name] is already existed")