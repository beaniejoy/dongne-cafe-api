package io.beaniejoy.dongnecafe.domain.cafe.dto.cafe

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.dto.menu.CafeMenuResponseDto

data class CafeInfoResponseDto(
    val id: Long? = null,
    val name: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val totalRate: Double? = null,
    val description: String? = null,
    val menuList: List<CafeMenuResponseDto> = emptyList(),
    val imageList: List<CafeImageResponseDto> = emptyList()
) {
    companion object {
        fun of(cafe: Cafe): CafeInfoResponseDto {
            return CafeInfoResponseDto(
                id = cafe.id,
                name = cafe.name,
                address = cafe.address,
                phoneNumber = cafe.phoneNumber,
                totalRate = cafe.totalRate,
                description = cafe.description,
                menuList = cafe.cafeMenuList.map { CafeMenuResponseDto.of(it) },
                imageList = cafe.cafeImageList.map { CafeImageResponseDto.of(it) }
            )
        }
    }
}