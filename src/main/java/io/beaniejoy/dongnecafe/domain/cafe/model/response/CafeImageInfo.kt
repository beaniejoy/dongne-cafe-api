package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeImage

data class CafeImageInfo(
    val imgUrl: String? = null
) {
    companion object {
        fun of(cafeImage: CafeImage): CafeImageInfo {
            return CafeImageInfo(cafeImage.imgUrl)
        }
    }
}