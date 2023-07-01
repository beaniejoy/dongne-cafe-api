package io.beaniejoy.dongnecafe.cafe.service.validator

import io.beaniejoy.dongnecafe.cafe.persistence.CafeReaderPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesReaderPort
import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import org.springframework.stereotype.Component

@Component
class CafeMenuCategoryCrudValidator(
    private val cafeReaderPort: CafeReaderPort,
    private val cafeSeriesReaderPort: CafeSeriesReaderPort
): CafeMenuCategoryValidator {
    override fun validateNotExisted(name: String, cafeId: Long) {
        if (cafeSeriesReaderPort.existsCafeMenuCategoryByName(name = name, cafeId = cafeId)) {
            throw BusinessException(ErrorCode.CAFE_MENU_CATEGORY_EXISTED)
        }
    }

    override fun validateTheSameCafe(cafeId: Long, menuCategoryId: Long) {
        val cafe = cafeReaderPort.getCafeNotNull(cafeId)
        val cafeMenuCategory = cafeSeriesReaderPort.getCafeMenuCategoryNotNull(menuCategoryId)
        cafe.checkTheSameAs(other = cafeMenuCategory.cafe)
    }

    override fun validateContainingAllMenus(menuCategoryId: Long, cafeMenuIds: List<Long>) {
        val cafeMenuCategory = cafeSeriesReaderPort.getCafeMenuCategoryNotNull(menuCategoryId)

        if (cafeMenuCategory.cafeMenus.map { it.id }.containsAll(cafeMenuIds).not()) {
            throw BusinessException(ErrorCode.CAFE_MENU_INVALID_REQUEST)
        }
    }
}