package io.beaniejoy.dongnecafe.domain.cafe.entity

import java.math.BigDecimal

class CafeInfo {
    data class RegisteredCafe(
        val cafeId: Long,
        val name: String
    )

    data class RegisteredCafeMenuCategory(
        val menuCategoryId: Long,
        val name: String
    )

    data class RegisteredCafeMenu(
        val cafeMenuId: Long,
        val name: String,
        val price: BigDecimal,
        val description: String,
        val menuOptions: List<RegisteredMenuOption> = emptyList()
    )

    data class RegisteredMenuOption(
        val menuOptionId: Long,
        val title: String,
        val optionDetails: List<RegisteredOptionDetail> = emptyList()
    )

    data class RegisteredOptionDetail(
        val optionDetailId: Long,
        val name: String,
        val extraPrice: BigDecimal
    )

    data class CafeSearchInfo(
        val cafeId: Long,
        val name: String,
        val address: String,
        val totalRate: Float,
        val cafeImages: List<ImageInfo> = emptyList()
    )

    data class CafeDetailInfo(
        val cafeId: Long,
        val name: String,
        val address: String,
        val phoneNumber: String,
        val totalRate: Float,
        val description: String,
        val cafeMenuCategories: List<CafeMenuCategoryInfo> = emptyList(),
        val cafeImages: List<ImageInfo> = emptyList()
    )

    data class CafeMenuCategoryInfo(
        val menuCategoryId: Long,
        val name: String,
        val description: String?,
        val cafeMenus: List<CafeMenuInfo> = emptyList(),
        val cafeMenuCategoryImages: List<ImageInfo> = emptyList()
    )

    data class CafeMenuInfo(
        val cafeMenuId: Long,
        val name: String,
        val price: BigDecimal,
        val description: String?,
        val menuOptions: List<MenuOptionInfo> = emptyList(),
        val cafeMenuImages: List<ImageInfo> = emptyList()
    )

    data class ImageInfo(
        val imageId: Long,
        val imgUrl: String
    )

    data class MenuOptionInfo(
        val menuOptionId: Long,
        val title: String,
        val optionDetails: List<OptionDetailInfo> = emptyList()
    )

    data class OptionDetailInfo(
        val optionDetailId: Long,
        val name: String,
        val extraPrice: BigDecimal
    )
}
