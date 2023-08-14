package io.beaniejoy.dongnecafe.domain.cafe.persistence

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenuCategory

interface CafeRemoverPort {
    fun deleteCafeMenuCategory(cafeMenuCategory: CafeMenuCategory)
}
