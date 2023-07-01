package io.beaniejoy.dongnecafe.cafe.model.response

import java.math.BigDecimal

class CafeOutputDto {
    data class RegisteredCafeResponse(
        val cafeId: Long,
        val name: String
    )

    data class RegisteredCafeMenuCategoryResponse(
        val menuCategoryId: Long,
        val name: String
    )

    data class RegisteredCafeMenuResponse(
        val cafeMenuId: Long,
        val name: String
    )

    data class CafeSearchResponse(
        val cafeId: Long,
        val name: String,
        val address: String,
        val totalRate: Float,
        val cafeImages: List<CafeImageResponse>
    )

    data class CafeImageResponse(
        val cafeImageId: Long,
        val imgUrl: String
    )

    data class CafeDetailedResponse(
        val cafeId: Long,
        val name: String,
        val address: String,
        val phoneNumber: String,
        val totalRate: Float,
        val description: String?,
        val cafeMenuCategories: List<CafeMenuCategoryResponse> = emptyList(),
        val cafeImages: List<CafeImageResponse> = emptyList()
    )

    data class CafeMenuCategoryResponse(
        val menuCategoryId: Long,
        val name: String,
        val description: String?,
        val cafeMenus: List<CafeMenuResponse> = emptyList(),
    )

    data class CafeMenuResponse(
        val cafeMenuId: Long,
        val name: String,
        val price: BigDecimal,
        val description: String?,
        val menuOptions: List<MenuOptionResponse> = emptyList()
    )

    data class MenuOptionResponse(
        val menuOptionId: Long,
        val title: String,
        val optionDetails: List<OptionDetailResponse> = emptyList()
    )

    data class OptionDetailResponse(
        val optionDetailId: Long,
        val name: String,
        val extraPrice: BigDecimal
    )
}