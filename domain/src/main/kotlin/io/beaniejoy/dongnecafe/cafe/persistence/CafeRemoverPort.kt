package io.beaniejoy.dongnecafe.cafe.persistence

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory

interface CafeRemoverPort {
    fun deleteCafeMenuCategory(cafeMenuCategory: CafeMenuCategory)
}