package io.beaniejoy.dongnecafe.cafe.model

import java.math.BigDecimal

class CafeSeriesCommand {
    data class UpdateCafeSeries(
        val cafeMenuCategories: List<BulkUpdateCafeMenuCategory>
    )

    data class BulkUpdateCafeMenuCategory(
        var menuCategoryId: Long? = null,
        val name: String,
        val description: String?,
        val isDelete: Boolean = false,          // 삭제 대상 여부
        val isNew: Boolean = false,             // 신규 생성 여부
        val cafeMenus: List<BulkUpdateCafeMenu> = emptyList()
    )

    data class BulkUpdateCafeMenu(
        val cafeMenuId: Long? = null,
        val name: String?,
        val price: BigDecimal?,
        val isDelete: Boolean = false,          // 삭제 대상 여부
        val isNew: Boolean = false              // 신규 생성 여부
    )
}