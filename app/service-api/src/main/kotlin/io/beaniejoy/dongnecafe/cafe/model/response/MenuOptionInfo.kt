package io.beaniejoy.dongnecafe.cafe.model.response

import io.beaniejoy.dongnecafe.cafe.entity.MenuOption

data class MenuOptionInfo(
    val menuOptionId: Long = 0L,
    val title: String? = null,
    val optionDetails: List<OptionDetailInfo> = emptyList()
) {
    companion object {
        fun of(menuOption: MenuOption): MenuOptionInfo {
            return MenuOptionInfo(
                menuOptionId = menuOption.id,
                title = menuOption.title,
                optionDetails = menuOption.optionDetails.map { OptionDetailInfo.of(it) }
            )
        }
    }
}