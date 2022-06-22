package io.beaniejoy.dongnecafe.domain.cafe.dto.menu

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import java.math.BigDecimal

data class CafeMenuResponseDto(
    val id: Long = 0L,
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
) {
    companion object {
        fun of(cafeMenu: CafeMenu): CafeMenuResponseDto {
            return CafeMenuResponseDto(
                id = cafeMenu.id,
                name = cafeMenu.name,
                price = cafeMenu.price
            )
        }
    }
}