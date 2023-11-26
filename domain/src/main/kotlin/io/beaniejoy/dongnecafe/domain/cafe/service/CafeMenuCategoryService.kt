package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeInfo

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
