package io.beaniejoy.dongnecafe.cafe.model.response

import io.beaniejoy.dongnecafe.cafe.entity.CafeImage

data class CafeImageInfo(
    val cafeImageId: Long = 0L,
    val imgUrl: String? = null
) {
    companion object {
        fun of(cafeImage: CafeImage): CafeImageInfo {
            return CafeImageInfo(cafeImage.id, cafeImage.imgUrl)
        }
    }
}