package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption

data class MenuOptionInfo(
    val id: Long = 0L,
    val title: String? = null,
    val optionDetailList: List<OptionDetailInfo> = emptyList()
) {
    companion object {
        fun of(menuOption: MenuOption): MenuOptionInfo {
            return MenuOptionInfo(
                id = menuOption.id,
                title = menuOption.title,
                optionDetailList = menuOption.optionDetailList.map { OptionDetailInfo.of(it) }
            )
        }
    }
}