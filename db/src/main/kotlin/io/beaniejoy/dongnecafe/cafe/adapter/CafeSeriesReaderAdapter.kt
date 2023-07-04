package io.beaniejoy.dongnecafe.cafe.adapter

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesReaderPort
import io.beaniejoy.dongnecafe.cafe.repository.CafeMenuCategoryRepository
import io.beaniejoy.dongnecafe.cafe.repository.CafeMenuRepository
import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CafeSeriesReaderAdapter(
    private val cafeMenuCategoryRepository: CafeMenuCategoryRepository,
    private val cafeMenuRepository: CafeMenuRepository
) : CafeSeriesReaderPort {
    override fun getCafeMenuCategory(id: Long): CafeMenuCategory? {
        return cafeMenuCategoryRepository.findByIdOrNull(id)
    }

    override fun getCafeMenuCategoryNotNull(id: Long): CafeMenuCategory {
        return cafeMenuCategoryRepository.findByIdOrNull(id)
            ?: throw BusinessException(ErrorCode.CAFE_MENU_CATEGORY_NOT_FOUND)
    }

    override fun getCafeMenuCategories(ids: List<Long>): List<CafeMenuCategory> {
        return cafeMenuCategoryRepository.findAllById(ids)
    }

    override fun existsCafeMenuCategoryByName(name: String, cafeId: Long): Boolean {
        return cafeMenuCategoryRepository.findByNameAndCafeId(
            name = name,
            cafeId = cafeId
        ) != null
    }

    override fun getCafeMenuCategoriesByCafeId(cafeId: Long): List<CafeMenuCategory> {
        return cafeMenuCategoryRepository.findAllByCafeId(cafeId)
    }

    override fun getCafeMenuNotNull(id: Long): CafeMenu {
        return cafeMenuRepository.findByIdOrNull(id)
            ?: throw BusinessException(ErrorCode.CAFE_MENU_NOT_FOUND)
    }

    override fun getCafeMenus(ids: List<Long>): List<CafeMenu> {
        return cafeMenuRepository.findAllById(ids)
    }

    override fun existsCafeMenuByName(name: String, menuCategoryIds: List<Long>): Boolean {
        return cafeMenuRepository.findByNameAndCafeMenuCategoryId(
            name = name,
            menuCategoryIds = menuCategoryIds
        ).isNotEmpty()
    }
}