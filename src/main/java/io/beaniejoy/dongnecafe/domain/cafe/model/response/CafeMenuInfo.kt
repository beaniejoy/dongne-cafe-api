package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import java.math.BigDecimal

data class CafeMenuInfo(
    val id: Long = 0L,
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
) {
    companion object {
        fun of(cafeMenu: CafeMenu): CafeMenuInfo {
            return CafeMenuInfo(
                id = cafeMenu.id,
                name = cafeMenu.name,
                price = cafeMenu.price
            )
        }
    }
}