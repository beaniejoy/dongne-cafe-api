package io.beaniejoy.dongnecafe.domain.cafe.service.validator

import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand

interface CafeMenuValidator {
    fun validateNotExisted(name: String, cafeId: Long)
    fun validateTheSameCategory(menuCategoryId: Long, menuId: Long)
    fun validateUpdateCommand(menuId: Long, command: CafeCommand.UpdateCafeMenu)
}
