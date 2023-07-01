package io.beaniejoy.dongnecafe.cafe.adapter

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesRemoverPort
import io.beaniejoy.dongnecafe.cafe.repository.CafeMenuCategoryRepository
import io.beaniejoy.dongnecafe.cafe.repository.CafeMenuRepository
import io.beaniejoy.dongnecafe.cafe.repository.MenuOptionRepository
import io.beaniejoy.dongnecafe.cafe.repository.OptionDetailRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CafeSeriesRemoverAdapter(
    private val cafeMenuRepository: CafeMenuRepository,
    private val cafeMenuCategoryRepository: CafeMenuCategoryRepository,
    private val menuOptionRepository: MenuOptionRepository,
    private val optionDetailRepository: OptionDetailRepository
): CafeSeriesRemoverPort {
    @Transactional
    override fun bulkDeleteCafeMenus(cafeMenus: List<CafeMenu>) {
        cafeMenuRepository.deleteAll(cafeMenus)
    }

    @Transactional
    override fun bulkDeleteCafeMenuCategories(cafeMenuCategories: List<CafeMenuCategory>) {
        cafeMenuCategoryRepository.deleteAll(cafeMenuCategories)
    }

    override fun bulkDeleteMenuOptions(menuOptionIds: List<Long>) {
        menuOptionRepository.deleteAllByIdInBatch(menuOptionIds)
    }

    @Transactional
    override fun bulkDeleteOptionDetails(optionDetailIds: List<Long>) {
        optionDetailRepository.deleteAllByIdInBatch(optionDetailIds)
    }
}