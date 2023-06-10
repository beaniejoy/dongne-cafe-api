package io.beaniejoy.dongnecafe.cafe.persistence

import io.beaniejoy.dongnecafe.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.cafe.model.CafeCommand

interface CafeSeriesStorePort {
    fun storeMenuCategorySeries(cafe: Cafe, registerCafe: CafeCommand.RegisterCafe): List<CafeMenuCategory>
}