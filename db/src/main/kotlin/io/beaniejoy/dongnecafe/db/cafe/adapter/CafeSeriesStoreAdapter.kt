package io.beaniejoy.dongnecafe.db.cafe.adapter

import io.beaniejoy.dongnecafe.db.cafe.repository.CafeMenuRepository
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeSeriesStorePort
import org.springframework.stereotype.Component

@Component
class CafeSeriesStoreAdapter(
    private val cafeMenuRepository: CafeMenuRepository
) : CafeSeriesStorePort {
    override fun storeCafeMenus(cafeMenus: List<CafeMenu>): List<CafeMenu> {
        return cafeMenuRepository.saveAll(cafeMenus)
    }
}
