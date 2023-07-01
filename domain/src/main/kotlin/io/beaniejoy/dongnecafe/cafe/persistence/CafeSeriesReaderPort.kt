package io.beaniejoy.dongnecafe.cafe.persistence

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory

interface CafeSeriesReaderPort {
    // CafeMenuCategory
    fun getCafeMenuCategory(id: Long): CafeMenuCategory?
    fun getCafeMenuCategoryNotNull(id: Long): CafeMenuCategory
    fun getCafeMenuCategories(ids: List<Long>): List<CafeMenuCategory>
    fun existsCafeMenuCategoryByName(name: String, cafeId: Long): Boolean

    // CafeMenu
    fun getCafeMenuNotNull(id: Long): CafeMenu
    fun getCafeMenus(ids: List<Long>): List<CafeMenu>
    fun existsCafeMenuByName(name: String, menuCategoryId: Long): Boolean
}