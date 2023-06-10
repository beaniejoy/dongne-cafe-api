package io.beaniejoy.dongnecafe.cafe.model

import java.math.BigDecimal


class CafeInputDto {
    // request body
    data class RegisterCafeRequest(
        val name: String?,
        val address: String?,
        val phoneNumber: String?,
        val description: String?,
        val cafeMenuCategories: List<RegisterCafeMenuCategoryRequest> = emptyList()
    )

    data class RegisterCafeMenuCategoryRequest(
        val name: String,
        val description: String,
        val cafeMenus: List<RegisterCafeMenuRequest> = emptyList()
    )

    data class RegisterCafeMenuRequest(
        val name: String = "",
        val price: BigDecimal = BigDecimal.ZERO,
        val menuOptions: List<RegisterMenuOptionRequest> = emptyList()
    )

    data class RegisterMenuOptionRequest(
        val title: String,
        val optionDetails: List<RegisterOptionDetailRequest> = emptyList()
    )

    data class RegisterOptionDetailRequest(
        val name: String,
        val extraPrice: BigDecimal
    )

    data class UpdateCafeRequest(
        val name: String?,
        val address: String?,
        val phoneNumber: String?,
        val description: String?
    )

    // request param
    data class SearchCafesParam(
        val name: String?,
        val address: String?
    )
}