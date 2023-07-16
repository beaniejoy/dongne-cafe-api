package io.beaniejoy.dongnecafe.domain.cafe.persistence

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.domain.cafe.entity.OptionDetail

interface CafeSeriesReaderPort {
    // CafeMenuCategory
    fun getCafeMenuCategory(id: Long): CafeMenuCategory?
    fun getCafeMenuCategoryNotNull(id: Long): CafeMenuCategory
    fun getCafeMenuCategories(ids: List<Long>): List<CafeMenuCategory>
    fun existsCafeMenuCategoryByName(name: String, cafeId: Long): Boolean
    fun getCafeMenuCategoriesByCafeId(cafeId: Long): List<CafeMenuCategory>

    // CafeMenu
    fun getCafeMenuNotNull(id: Long): CafeMenu
    fun getCafeMenus(ids: List<Long>): List<CafeMenu>
    fun existsCafeMenuByName(name: String, menuCategoryIds: List<Long>): Boolean

    // OptionDetail
    fun getOptionDetails(ids: List<Long>): List<OptionDetail>
}
