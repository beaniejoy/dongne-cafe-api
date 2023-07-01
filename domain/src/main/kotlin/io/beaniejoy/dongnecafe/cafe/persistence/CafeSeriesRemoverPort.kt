package io.beaniejoy.dongnecafe.cafe.persistence

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory

interface CafeSeriesRemoverPort {
    fun bulkDeleteCafeMenus(cafeMenus: List<CafeMenu>)

    fun bulkDeleteCafeMenuCategories(cafeMenuCategories: List<CafeMenuCategory>)

    fun bulkDeleteMenuOptions(menuOptionIds: List<Long>)

    fun bulkDeleteOptionDetails(optionDetailIds: List<Long>)
}