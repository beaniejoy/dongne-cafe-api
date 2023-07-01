package io.beaniejoy.dongnecafe.cafe.persistence

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu

interface CafeSeriesStorePort {
    fun storeCafeMenus(cafeMenus: List<CafeMenu>): List<CafeMenu>
}