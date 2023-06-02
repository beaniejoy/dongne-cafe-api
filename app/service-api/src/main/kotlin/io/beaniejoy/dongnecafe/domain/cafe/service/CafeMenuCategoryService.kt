package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeMenuCategoryRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class CafeMenuCategoryService(
    private val cafeMenuCategoryRepository: CafeMenuCategoryRepository,
    private val cafeMenuService: CafeMenuService
) {

    fun createNewOfCafe(
        name: String,
        description: String,
        cafeMenuRegisterRequests: List<CafeMenuRegisterRequest>,
        cafe: Cafe
    ): CafeMenuCategory {
        val cafeMenuCategory = CafeMenuCategory.createEntity(
            name = name,
            description = description
        ).apply {
            this.updateCafe(cafe)
        }

        val savedMenuCategory = cafeMenuCategoryRepository.save(cafeMenuCategory)

        // category 소속 하위 메뉴들에 대한 save 진행
        cafeMenuRegisterRequests.forEach {
            cafeMenuService.createNewOfCategory(
                name = it.name,
                price = it.price,
                menuOptionRegisterRequests = it.menuOptions,
                menuCategory = savedMenuCategory
            )
        }

        return savedMenuCategory
    }
}