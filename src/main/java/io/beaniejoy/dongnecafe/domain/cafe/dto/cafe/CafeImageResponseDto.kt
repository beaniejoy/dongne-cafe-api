package io.beaniejoy.dongnecafe.domain.cafe.dto.cafe

import io.beaniejoy.dongnecafe.domain.cafe.domain.CafeImage

data class CafeImageResponseDto(
    val imgUrl: String? = null
) {
    companion object {
        fun of(cafeImage: CafeImage): CafeImageResponseDto {
            return CafeImageResponseDto(cafeImage.imgUrl)
        }
    }
}