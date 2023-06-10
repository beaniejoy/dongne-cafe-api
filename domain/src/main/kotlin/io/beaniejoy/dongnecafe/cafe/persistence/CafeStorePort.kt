package io.beaniejoy.dongnecafe.cafe.persistence

import io.beaniejoy.dongnecafe.cafe.entity.*

interface CafeStorePort {
    fun store(cafe: Cafe): Cafe

    fun store(cafeMenuCategory: CafeMenuCategory): CafeMenuCategory

    fun store(cafeMenu: CafeMenu): CafeMenu

    fun store(menuOption: MenuOption): MenuOption

    fun store(optionDetail: OptionDetail): OptionDetail
}