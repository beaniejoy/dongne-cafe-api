package io.beaniejoy.dongnecafe.domain.cafe.error

class CafeNotFoundException(cafeId: Long) : RuntimeException("Cafe[$cafeId] is not found")