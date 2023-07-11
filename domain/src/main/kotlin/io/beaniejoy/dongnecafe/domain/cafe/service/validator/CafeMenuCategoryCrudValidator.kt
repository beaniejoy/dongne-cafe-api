package io.beaniejoy.dongnecafe.domain.cafe.service.validator

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeReaderPort
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeSeriesReaderPort
import org.springframework.stereotype.Component

@Component
class CafeMenuCategoryCrudValidator(
    private val cafeReaderPort: CafeReaderPort,
    private val cafeSeriesReaderPort: CafeSeriesReaderPort
): CafeMenuCategoryValidator {
    override fun validateNotExisted(name: String, cafeId: Long) {
        check(cafeSeriesReaderPort.existsCafeMenuCategoryByName(name = name, cafeId = cafeId).not()) {
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
        val originCafeMenuIds = cafeMenuCategory.cafeMenus.map { it.id }

        // Category에 속한 CafeMenu 목록에 모두 포함하는지 check
        check(originCafeMenuIds.containsAll(cafeMenuIds)) {
            throw BusinessException(ErrorCode.CAFE_MENU_INVALID_REQUEST)
        }
    }
}