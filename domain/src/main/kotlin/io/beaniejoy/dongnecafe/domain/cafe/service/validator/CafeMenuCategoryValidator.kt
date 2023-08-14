package io.beaniejoy.dongnecafe.domain.cafe.service.validator

interface CafeMenuCategoryValidator {
    fun validateNotExisted(name: String, cafeId: Long)

    fun validateTheSameCafe(cafeId: Long, menuCategoryId: Long)

    fun validateContainingAllMenus(menuCategoryId: Long, cafeMenuIds: List<Long>)
}
