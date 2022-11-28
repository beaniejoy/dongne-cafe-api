package io.beaniejoy.dongnecafe.error.exception

class CafeMenuNotFoundException(menuId: Long, cafeId: Long) :
    RuntimeException("Cafe[${cafeId}]의 Menu[${menuId}]는 존재하지 않는 메뉴입니다.")