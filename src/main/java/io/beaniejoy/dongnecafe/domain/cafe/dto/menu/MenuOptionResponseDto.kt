package io.beaniejoy.dongnecafe.domain.cafe.dto.menu

import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption

data class MenuOptionResponseDto(
    val id: Long = 0L,
    val title: String? = null,
    val optionDetailList: List<OptionDetailResponseDto> = emptyList()
) {
    companion object {
        fun of(menuOption: MenuOption): MenuOptionResponseDto {
            return MenuOptionResponseDto(
                id = menuOption.id,
                title = menuOption.title,
                optionDetailList = menuOption.optionDetailList.map { OptionDetailResponseDto.of(it) }
            )
        }
    }
}