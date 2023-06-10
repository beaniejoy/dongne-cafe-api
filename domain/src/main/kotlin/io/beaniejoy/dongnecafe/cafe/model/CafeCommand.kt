package io.beaniejoy.dongnecafe.cafe.model

import java.math.BigDecimal

class CafeCommand {
    data class RegisterCafe(
        val name: String,
        val address: String,
        val phoneNumber: String,
        val description: String,
        val cafeMenuCategories: List<RegisterCafeMenuCategory>
    )

    data class RegisterCafeMenuCategory(
        val name: String,
        val description: String,
        val cafeMenus: List<RegisterCafeMenu> = arrayListOf()
    )

    data class RegisterCafeMenu(
        val name: String = "",
        val price: BigDecimal = BigDecimal.ZERO,
        val menuOptions: List<RegisterMenuOption> = arrayListOf()
    )

    data class RegisterMenuOption(
        val title: String,
        val optionDetails: List<RegisterOptionDetail> = arrayListOf()
    )

    data class RegisterOptionDetail(
        val name: String,
        val extraPrice: BigDecimal
    )

    data class UpdateCafe(
        val name: String,
        val address: String,
        val phoneNumber: String,
        val description: String
    )
}