package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe

data class CafeSearchInfo(
    val cafeId: Long = 0L,
    val name: String? = null,
    val address: String? = null,
    val totalRate: Float? = null,
    val images: List<CafeImageInfo> = emptyList()
) {
    companion object {
        fun of(cafe: Cafe): CafeSearchInfo {
            return CafeSearchInfo(
                cafeId = cafe.id,
                name = cafe.name,
                address = cafe.address,
                totalRate = cafe.totalRate.toFloat(),
                images = cafe.cafeImages.map { CafeImageInfo.of(it) }
            )
        }
    }
}