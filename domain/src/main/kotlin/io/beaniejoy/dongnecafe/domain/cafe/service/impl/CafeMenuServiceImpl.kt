package io.beaniejoy.dongnecafe.domain.cafe.service.impl

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeInfo
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeInfoMapper
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeSeriesReaderPort
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeStorePort
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeMenuService
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeSeriesProcessor
import io.beaniejoy.dongnecafe.domain.cafe.service.validator.CafeMenuCategoryValidator
import io.beaniejoy.dongnecafe.domain.cafe.service.validator.CafeMenuValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeMenuServiceImpl(
    private val cafeSeriesReaderPort: CafeSeriesReaderPort,
    private val cafeStorePort: CafeStorePort,
    private val cafeInfoMapper: CafeInfoMapper,
    private val cafeMenuCategoryValidator: CafeMenuCategoryValidator,
    private val cafeMenuValidator: CafeMenuValidator,
    private val cafeSeriesProcessor: CafeSeriesProcessor
) : CafeMenuService {

    @Transactional
    override fun registerCafeMenu(
        cafeId: Long,
        menuCategoryId: Long,
        command: CafeCommand.RegisterCafeMenu
    ): CafeInfo.RegisteredCafeMenu {
        // 1. validate id and command
        cafeMenuCategoryValidator.validateTheSameCafe(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId
        )

        cafeMenuValidator.validateNotExisted(
            name = command.name,
            cafeId = cafeId
        )

        // 2. select target CafeMenuCategory
        val cafeMenuCategory = cafeSeriesReaderPort.getCafeMenuCategoryNotNull(menuCategoryId)

        // 3. save new CafeMenu
        val savedCafeMenu = cafeStorePort.store(
            CafeMenu.createEntity(command).apply {
                this.updateCafeMenuCategory(cafeMenuCategory)
            }
        )

        // 4. bulk save new CafeMenu's series (MenuOptions, OptionDetails)
        val savedMenuOptions = cafeSeriesProcessor.bulkSaveCafeMenuSeries(
            cafeMenu = savedCafeMenu,
            commands = command.menuOptions
        )

        return cafeInfoMapper.of(
            cafeMenu = savedCafeMenu,
            menuOptions = savedMenuOptions
        )
    }

    @Transactional
    override fun updateCafeMenuWithSeries(
        cafeId: Long,
        menuCategoryId: Long,
        menuId: Long,
        command: CafeCommand.UpdateCafeMenu
    ) {
        // 1. validate id and command
        cafeMenuCategoryValidator.validateTheSameCafe(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId
        )

        cafeMenuValidator.validateTheSameCategory(
            menuCategoryId = menuCategoryId,
            menuId = menuId
        )

        cafeMenuValidator.validateUpdateCommand(
            menuId = menuId,
            command = command
        )

        val cafeMenu = cafeSeriesReaderPort.getCafeMenuNotNull(menuId)

        // 2. update CafeMenu and CafeMenu's Series(MenuOptions, OptionDetails) bulk update
        cafeMenu.updateWithSeries(command)

        // 3. bulk delete CafeMenu's Series(MenuOptions, OptionDetails)
        cafeSeriesProcessor.bulkDeleteCafeMenuSeriesWithFiltered(command.menuOptions)
    }

    @Transactional
    override fun bulkDeleteCafeMenus(
        cafeId: Long,
        menuCategoryId: Long,
        deleteMenuIds: List<Long>
    ) {
        // 1. validate id and command
        cafeMenuCategoryValidator.validateTheSameCafe(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId
        )

        cafeMenuCategoryValidator.validateContainingAllMenus(
            menuCategoryId,
            deleteMenuIds
        )

        // 2. bulk delete CafeMenus
        cafeSeriesProcessor.bulkDeleteCafeMenusWithSeries(deleteMenuIds)
    }
}