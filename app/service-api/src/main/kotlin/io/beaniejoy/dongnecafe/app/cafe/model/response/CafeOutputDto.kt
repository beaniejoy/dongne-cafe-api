package io.beaniejoy.dongnecafe.app.cafe.model.response

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
        val name: String,
        val price: BigDecimal,
        val description: String,
        val menuOptions: List<RegisteredMenuOptionResponse> = emptyList()
    )

    data class RegisteredMenuOptionResponse(
        val menuOptionId: Long,
        val title: String,
        val optionDetails: List<RegisteredOptionDetailResponse> = emptyList()
    )

    data class RegisteredOptionDetailResponse(
        val optionDetailId: Long,
        val name: String,
        val extraPrice: BigDecimal
    )

    data class CafeSearchResponse(
        val cafeId: Long,
        val name: String,
        val address: String,
        val totalRate: Float,
        val cafeImages: List<ImageResponse>
    )

    data class CafeDetailedResponse(
        val cafeId: Long,
        val name: String,
        val address: String,
        val phoneNumber: String,
        val totalRate: Float,
        val description: String?,
        val cafeMenuCategories: List<CafeMenuCategoryResponse> = emptyList(),
        val cafeImages: List<ImageResponse> = emptyList()
    )

    data class CafeMenuCategoryResponse(
        val menuCategoryId: Long,
        val name: String,
        val description: String?,
        val cafeMenus: List<CafeMenuResponse> = emptyList(),
        val cafeMenuCategoryImages: List<ImageResponse> = emptyList()
    )

    data class CafeMenuResponse(
        val cafeMenuId: Long,
        val name: String,
        val price: BigDecimal,
        val description: String?,
        val menuOptions: List<MenuOptionResponse> = emptyList(),
        val cafeMenuImages: List<ImageResponse> = emptyList()
    )

    data class ImageResponse(
        val imageId: Long,
        val imgUrl: String
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
