package io.beaniejoy.dongnecafe.domain.cafe.persistence

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu

interface CafeSeriesStorePort {
    fun storeCafeMenus(cafeMenus: List<CafeMenu>): List<CafeMenu>
}