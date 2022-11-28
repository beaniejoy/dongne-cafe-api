package io.beaniejoy.dongnecafe.error.exception

class OptionDetailNotFoundException(optionDetailId: Long) : RuntimeException("OptionDetail[$optionDetailId] is not found")