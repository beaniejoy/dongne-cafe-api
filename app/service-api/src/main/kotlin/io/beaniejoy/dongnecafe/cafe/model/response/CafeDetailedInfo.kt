package io.beaniejoy.dongnecafe.cafe.model.response

import io.beaniejoy.dongnecafe.cafe.entity.Cafe

data class CafeDetailedInfo(
    val cafeId: Long = 0L,
    val name: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val totalRate: Float? = null,
    val description: String? = null,
    val cafeMenuCategories: List<CafeMenuCategoryInfo> = emptyList(),
    val images: List<CafeImageInfo> = emptyList()
) {
    companion object {
        fun of(cafe: Cafe): CafeDetailedInfo {
            return CafeDetailedInfo(
                cafeId = cafe.id,
                name = cafe.name,
                address = cafe.address,
                phoneNumber = cafe.phoneNumber,
                totalRate = cafe.totalRate.toFloat(),
                description = cafe.description,
                cafeMenuCategories = cafe.cafeMenuCategories.map {
                    CafeMenuCategoryInfo.of(
                        it
                    )
                },
                images = cafe.cafeImages.map { CafeImageInfo.of(it) }
            )
        }
    }
}