package io.beaniejoy.dongnecafe.common.error.exception

class OptionDetailNotFoundException(optionDetailId: Long) : RuntimeException("OptionDetail[$optionDetailId] is not found")