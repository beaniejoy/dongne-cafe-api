package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeInfo

interface CafeMenuService {
    fun registerCafeMenu(
        cafeId: Long,
        menuCategoryId: Long,
        command: CafeCommand.RegisterCafeMenu
    ): CafeInfo.RegisteredCafeMenu

    fun updateCafeMenuWithSeries(
        cafeId: Long,
        menuCategoryId: Long,
        menuId: Long,
        command: CafeCommand.UpdateCafeMenu
    )

    fun bulkDeleteCafeMenus(
        cafeId: Long,
        menuCategoryId: Long,
        deleteMenuIds: List<Long>
    )
}
