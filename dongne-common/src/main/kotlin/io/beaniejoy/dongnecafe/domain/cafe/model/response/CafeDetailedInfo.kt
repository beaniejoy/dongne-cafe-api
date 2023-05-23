package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe

data class CafeDetailedInfo(
    val cafeId: Long = 0L,
    val name: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val totalRate: Float? = null,
    val description: String? = null,
    val menus: List<CafeMenuInfo> = emptyList(),
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
                menus = cafe.cafeMenus.map { CafeMenuInfo.of(it) },
                images = cafe.cafeImages.map { CafeImageInfo.of(it) }
            )
        }
    }
}