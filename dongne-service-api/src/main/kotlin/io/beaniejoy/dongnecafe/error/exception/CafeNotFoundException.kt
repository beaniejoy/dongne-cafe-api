package io.beaniejoy.dongnecafe.error.exception

class CafeNotFoundException(cafeId: Long) : RuntimeException("Cafe[$cafeId] is not found")