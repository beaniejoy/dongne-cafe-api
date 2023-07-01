package io.beaniejoy.dongnecafe.cafe.service

import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.cafe.model.CafeInfo

interface CafeMenuCategoryService {
    fun registerMenuCategory(
        cafeId: Long,
        command: CafeCommand.RegisterCafeMenuCategory
    ): CafeInfo.RegisteredCafeMenuCategory

    fun updateMenuCategory(
        cafeId: Long,
        menuCategoryId: Long,
        command: CafeCommand.UpdateCafeMenuCategory
    )

    fun deleteMenuCategory(cafeId: Long, menuCategoryId: Long)
}