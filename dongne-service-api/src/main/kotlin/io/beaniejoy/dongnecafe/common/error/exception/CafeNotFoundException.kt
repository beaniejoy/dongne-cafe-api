package io.beaniejoy.dongnecafe.common.error.exception

class CafeNotFoundException(cafeId: Long) : RuntimeException("Cafe[$cafeId] is not found")