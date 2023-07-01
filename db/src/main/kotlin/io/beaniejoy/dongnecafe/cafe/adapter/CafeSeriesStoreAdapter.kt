package io.beaniejoy.dongnecafe.cafe.adapter

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesStorePort
import io.beaniejoy.dongnecafe.cafe.repository.CafeMenuCategoryRepository
import io.beaniejoy.dongnecafe.cafe.repository.CafeMenuRepository
import org.springframework.stereotype.Component

@Component
class CafeSeriesStoreAdapter(
    private val cafeMenuRepository: CafeMenuRepository
) : CafeSeriesStorePort {
    override fun storeCafeMenus(cafeMenus: List<CafeMenu>): List<CafeMenu> {
        return cafeMenuRepository.saveAll(cafeMenus)
    }

}