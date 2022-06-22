package io.beaniejoy.dongnecafe.domain.cafe.dto.menu

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import java.math.BigDecimal

data class CafeMenuDetailResponseDto(
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val optionList: List<MenuOptionResponseDto> = emptyList()
) {
    companion object {
        fun of(cafeMenu: CafeMenu): CafeMenuDetailResponseDto {
            return CafeMenuDetailResponseDto(
                name = cafeMenu.name,
                price = cafeMenu.price,
                optionList = cafeMenu.menuOptionList.map { MenuOptionResponseDto.of(it) }
            )
        }
    }
}