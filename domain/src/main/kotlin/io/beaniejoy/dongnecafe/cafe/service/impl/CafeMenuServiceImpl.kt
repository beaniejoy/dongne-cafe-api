package io.beaniejoy.dongnecafe.cafe.service.impl

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.cafe.model.CafeInfo
import io.beaniejoy.dongnecafe.cafe.model.CafeInfoMapper
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesReaderPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeStorePort
import io.beaniejoy.dongnecafe.cafe.service.CafeMenuService
import io.beaniejoy.dongnecafe.cafe.service.CafeSeriesProcessor
import io.beaniejoy.dongnecafe.cafe.service.validator.CafeMenuCategoryValidator
import io.beaniejoy.dongnecafe.cafe.service.validator.CafeMenuValidator
import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
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
            menuCategoryId = menuCategoryId
        )

        // 2. save new CafeMenu
        val savedCafeMenu = cafeStorePort.store(
            CafeMenu.createEntity(command).apply {
                this.updateCafeMenuCategory(cafeMenuCategory)
            }
        )

        return cafeInfoMapper.of(savedCafeMenu)
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
        cafeSeriesProcessor.bulkDeleteCafeMenuSeries(command.menuOptions)
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
        cafeSeriesProcessor.bulkDeleteCafeMenus(deleteMenuIds)
    }
}