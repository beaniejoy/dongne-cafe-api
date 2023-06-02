package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenuCategory

data class CafeMenuCategoryInfo(
    val menuCategoryId: Long,
    val name: String,
    val description: String,
    val cafeMenus: List<CafeMenuInfo> = emptyList(),
) {
    companion object {
        fun of(cafeMenuCategory: CafeMenuCategory): CafeMenuCategoryInfo {
            return CafeMenuCategoryInfo(
                menuCategoryId = cafeMenuCategory.id,
                name = cafeMenuCategory.name,
                description = cafeMenuCategory.description,
                cafeMenus = cafeMenuCategory.cafeMenus.map { CafeMenuInfo.of(it) },
            )
        }
    }
}