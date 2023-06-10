package io.beaniejoy.dongnecafe.cafe.model

class CafeOutputDto {
    data class RegisteredCafe(
        val cafeId: Long,
        val name: String
    )

    data class CafeSearchResponse(
        val cafeId: Long,
        val name: String,
        val address: String,
        val totalRate: Float,
        val cafeImages: List<CafeImageInfo>
    )

    data class CafeImageInfo(
        val cafeImageId: Long,
        val imgUrl: String
    )

    data class CafeDetailedResponse(
        val cafeId: Long,
        val name: String,
        val address: String,
        val phoneNumber: String,
        val totalRate: Float,
        val description: String? = null,
        val cafeMenuCategories: List<CafeInfo.CafeMenuCategoryInfo> = emptyList(),
        val cafeImages: List<CafeInfo.CafeImageInfo> = emptyList()
    )
}