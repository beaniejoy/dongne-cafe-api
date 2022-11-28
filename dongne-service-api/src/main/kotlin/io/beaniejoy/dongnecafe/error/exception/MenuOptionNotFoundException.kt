package io.beaniejoy.dongnecafe.error.exception

class MenuOptionNotFoundException(menuOptionId: Long) : RuntimeException("MenuOption[$menuOptionId] is not found")