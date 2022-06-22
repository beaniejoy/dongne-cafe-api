package io.beaniejoy.dongnecafe.domain.cafe.dto.cafe

import io.beaniejoy.dongnecafe.domain.cafe.domain.Cafe

data class CafeSearchResponseDto(
    val id: Long = 0L,
    val name: String? = null,
    val address: String? = null,
    val totalRate: Double? = null,
    val imageList: List<CafeImageResponseDto> = emptyList()
) {
    companion object {
        fun of(cafe: Cafe): CafeSearchResponseDto {
            return CafeSearchResponseDto(
                id = cafe.id,
                name = cafe.name,
                address = cafe.address,
                totalRate = cafe.totalRate,
                imageList = cafe.cafeImageList.map { CafeImageResponseDto.of(it) }
            )
        }
    }
}