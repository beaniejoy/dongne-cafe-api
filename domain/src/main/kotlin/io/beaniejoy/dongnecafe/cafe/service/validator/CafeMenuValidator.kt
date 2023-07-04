package io.beaniejoy.dongnecafe.cafe.service.validator

import io.beaniejoy.dongnecafe.cafe.model.CafeCommand

interface CafeMenuValidator {
    fun validateNotExisted(name: String, cafeId: Long)
    fun validateTheSameCategory(menuCategoryId: Long, menuId: Long)
    fun validateUpdateCommand(menuId: Long, command: CafeCommand.UpdateCafeMenu)
}