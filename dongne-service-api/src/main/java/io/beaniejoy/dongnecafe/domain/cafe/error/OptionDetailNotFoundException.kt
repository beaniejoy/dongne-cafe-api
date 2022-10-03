package io.beaniejoy.dongnecafe.domain.cafe.error

class OptionDetailNotFoundException(optionDetailId: Long) : RuntimeException("OptionDetail[$optionDetailId] is not found")