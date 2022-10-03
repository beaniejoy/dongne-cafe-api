package io.beaniejoy.dongnecafe.domain.cafe.error

class MenuOptionNotFoundException(menuOptionId: Long) : RuntimeException("MenuOption[$menuOptionId] is not found")