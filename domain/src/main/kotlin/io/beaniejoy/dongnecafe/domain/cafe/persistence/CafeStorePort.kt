package io.beaniejoy.dongnecafe.domain.cafe.persistence

import io.beaniejoy.dongnecafe.domain.cafe.entity.*

interface CafeStorePort {
    fun store(cafe: Cafe): Cafe

    fun store(cafeMenuCategory: CafeMenuCategory): CafeMenuCategory

    fun store(cafeMenu: CafeMenu): CafeMenu

    fun store(menuOption: MenuOption): MenuOption

    fun store(optionDetail: OptionDetail): OptionDetail
}