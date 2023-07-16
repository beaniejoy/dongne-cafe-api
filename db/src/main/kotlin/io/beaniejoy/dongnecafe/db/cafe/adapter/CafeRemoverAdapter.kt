package io.beaniejoy.dongnecafe.db.cafe.adapter

import io.beaniejoy.dongnecafe.db.cafe.repository.CafeMenuCategoryRepository
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeRemoverPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CafeRemoverAdapter(
    private val cafeMenuCategoryRepository: CafeMenuCategoryRepository
): CafeRemoverPort {
    override fun deleteCafeMenuCategory(cafeMenuCategory: CafeMenuCategory) {
        cafeMenuCategoryRepository.delete(cafeMenuCategory)
    }
}