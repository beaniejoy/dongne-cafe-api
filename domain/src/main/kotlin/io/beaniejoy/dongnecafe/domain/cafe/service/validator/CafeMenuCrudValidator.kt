package io.beaniejoy.dongnecafe.domain.cafe.service.validator

import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeSeriesReaderPort
import org.springframework.stereotype.Component

@Component
class CafeMenuCrudValidator(
    private val cafeSeriesReaderPort: CafeSeriesReaderPort
) : CafeMenuValidator {
    override fun validateNotExisted(name: String, cafeId: Long) {
        val cafeMenuCategories = cafeSeriesReaderPort.getCafeMenuCategoriesByCafeId(cafeId)

        val checkExistedMenu = cafeSeriesReaderPort.existsCafeMenuByName(
            name = name,
            menuCategoryIds = cafeMenuCategories.map { it.id }
        )

        if (checkExistedMenu) {
            throw BusinessException(ErrorCode.CAFE_MENU_EXISTED)
        }
    }

    override fun validateTheSameCategory(menuCategoryId: Long, menuId: Long) {
        val cafeMenuCategory = cafeSeriesReaderPort.getCafeMenuCategoryNotNull(menuCategoryId)
        val cafeMenu = cafeSeriesReaderPort.getCafeMenuNotNull(menuId)
        cafeMenuCategory.checkTheSameAs(other = cafeMenu.cafeMenuCategory)
    }

    override fun validateUpdateCommand(menuId: Long, command: CafeCommand.UpdateCafeMenu) {
        val cafeMenu = cafeSeriesReaderPort.getCafeMenuNotNull(menuId)

        // 1. validate MenuOptions of update command
        val originMenuOptionIds = cafeMenu.menuOptions.map { it.id }
        val commandMenuOptionIds = command.menuOptions.map { it.menuOptionId }

        if (originMenuOptionIds.containsAll(commandMenuOptionIds).not()) {
            throw BusinessException(ErrorCode.MENU_OPTION_INVALID_REQUEST)
        }

        // 2. validate OptionDetails of update command
        val originOptionDetailIds = cafeMenu.menuOptions
            .map { menuOption -> menuOption.optionDetails.map { it.id } }
            .flatten()
        val commandOptionDetailIds = command.menuOptions
            .map { updateMenuOption -> updateMenuOption.optionDetails.map { it.optionDetailId } }
            .flatten()

        if (originOptionDetailIds.containsAll(commandOptionDetailIds).not()) {
            throw BusinessException(ErrorCode.OPTION_DETAIL_INVALID_REQUEST)
        }
    }
}
