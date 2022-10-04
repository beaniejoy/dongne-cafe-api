package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import java.math.BigDecimal

data class CafeMenuInfo(
    val cafeMenuId: Long = 0L,
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
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