package io.beaniejoy.dongnecafe.cafe.model.response

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import java.math.BigDecimal

data class CafeMenuInfo(
    val cafeMenuId: Long = 0L,
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val menuOptions: List<MenuOptionInfo> = emptyList()
) {
    companion object {
        fun of(cafeMenu: CafeMenu): CafeMenuInfo {
            return CafeMenuInfo(
                cafeMenuId = cafeMenu.id,
                name = cafeMenu.name,
                price = cafeMenu.price
            )
        }
    }
}