package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import java.math.BigDecimal

data class CafeMenuDetailedInfo(
    val cafeMenuId: Long = 0L,
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val optionList: List<MenuOptionInfo> = emptyList()
) {
    companion object {
        fun of(cafeMenu: CafeMenu): CafeMenuDetailedInfo {
            return CafeMenuDetailedInfo(
                cafeMenuId = cafeMenu.id,
                name = cafeMenu.name,
                price = cafeMenu.price,
                optionList = cafeMenu.menuOptionList.map { MenuOptionInfo.of(it) }
            )
        }
    }
}