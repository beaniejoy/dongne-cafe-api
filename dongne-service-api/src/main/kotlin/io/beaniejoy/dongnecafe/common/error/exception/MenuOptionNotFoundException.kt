package io.beaniejoy.dongnecafe.common.error.exception

class MenuOptionNotFoundException(menuOptionId: Long) : RuntimeException("MenuOption[$menuOptionId] is not found")