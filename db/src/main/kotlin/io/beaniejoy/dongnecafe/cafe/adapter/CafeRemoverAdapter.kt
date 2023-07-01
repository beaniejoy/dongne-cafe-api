package io.beaniejoy.dongnecafe.cafe.adapter

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.cafe.persistence.CafeRemoverPort
import io.beaniejoy.dongnecafe.cafe.repository.CafeMenuCategoryRepository
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