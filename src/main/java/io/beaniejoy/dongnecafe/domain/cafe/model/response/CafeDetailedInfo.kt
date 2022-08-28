package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe

data class CafeDetailedInfo(
    val cafeId: Long = 0L,
    val name: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val totalRate: Double? = null,
    val description: String? = null,
    val menuList: List<CafeMenuInfo> = emptyList(),
    val imageList: List<CafeImageInfo> = emptyList()
) {
    companion object {
        fun of(cafe: Cafe): CafeDetailedInfo {
            return CafeDetailedInfo(
                cafeId = cafe.id,
                name = cafe.name,
                address = cafe.address,
                phoneNumber = cafe.phoneNumber,
                totalRate = cafe.totalRate,
                description = cafe.description,
                menuList = cafe.cafeMenuList.map { CafeMenuInfo.of(it) },
                imageList = cafe.cafeImageList.map { CafeImageInfo.of(it) }
            )
        }
    }
}