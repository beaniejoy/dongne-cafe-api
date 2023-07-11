package io.beaniejoy.dongnecafe.db.cafe.adapter

import io.beaniejoy.dongnecafe.db.cafe.repository.CafeMenuRepository
import io.beaniejoy.dongnecafe.db.cafe.repository.MenuOptionRepository
import io.beaniejoy.dongnecafe.db.cafe.repository.OptionDetailRepository
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeSeriesRemoverPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CafeSeriesRemoverAdapter(
    private val cafeMenuRepository: CafeMenuRepository,
    private val menuOptionRepository: MenuOptionRepository,
    private val optionDetailRepository: OptionDetailRepository
): CafeSeriesRemoverPort {
    override fun bulkDeleteCafeMenusInBatch(cafeMenuIds: List<Long>) {
        cafeMenuRepository.deleteAllByIdInBatch(cafeMenuIds)
    }

    override fun bulkDeleteMenuOptionsInBatch(menuOptionIds: List<Long>) {
        menuOptionRepository.deleteAllByIdInBatch(menuOptionIds)
    }

    override fun bulkDeleteOptionDetailsInBatch(optionDetailIds: List<Long>) {
        optionDetailRepository.deleteAllByIdInBatch(optionDetailIds)
    }
}