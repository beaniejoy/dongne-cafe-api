package io.beaniejoy.dongnecafe.domain.cafe.service.impl

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption
import io.beaniejoy.dongnecafe.domain.cafe.entity.OptionDetail
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeSeriesReaderPort
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeSeriesRemoverPort
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeStorePort
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeSeriesProcessor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeSeriesProcessorImpl(
    private val cafeStorePort: CafeStorePort,
    private val cafeSeriesReaderPort: CafeSeriesReaderPort,
    private val cafeSeriesRemoverPort: CafeSeriesRemoverPort
) : CafeSeriesProcessor {
    @Transactional
    override fun bulkSaveCafeMenuSeries(
        cafeMenu: CafeMenu,
        commands: List<CafeCommand.RegisterMenuOption>
    ): List<MenuOption> {
        return commands.map { command ->
            // 1. bulk save MenuOptions with saved CafeMenu
            cafeStorePort.store(
                MenuOption.createEntity(command).apply {
                    this.updateCafeMenu(cafeMenu)
                }
            ).also { savedMenuOption ->
                // 2. bulk save OptionDetails with saved MenuOption
                bulkSaveOptionDetails(savedMenuOption, command.optionDetails)
            }
        }
    }

    private fun bulkSaveOptionDetails(menuOption: MenuOption, commands: List<CafeCommand.RegisterOptionDetail>) {
        commands.forEach { command ->
            cafeStorePort.store(
                OptionDetail.createEntity(command).apply {
                    this.updateMenuOption(menuOption)
                }
            )
        }
    }

    @Transactional
    override fun bulkDeleteCafeMenuSeriesWithFiltered(commands: List<CafeCommand.UpdateMenuOption>) {
        // 1. bulk delete OptionDetails (immediately run query)
        val deleteOptionDetailIds = commands.flatMap { command ->
            command.optionDetails
                .filter { it.delete }
                .map { it.optionDetailId }
        }

        // (immediately run query)
        cafeSeriesRemoverPort.bulkDeleteOptionDetailsInBatch(deleteOptionDetailIds)

        // 2. bulk delete MenuOptions
        val deleteMenuOptionIds = commands
            .filter { it.delete }
            .map { it.menuOptionId }

        // (immediately run query)
        cafeSeriesRemoverPort.bulkDeleteMenuOptionsInBatch(deleteMenuOptionIds)
    }

    @Transactional
    override fun bulkDeleteCafeMenusWithSeries(menuIds: List<Long>) {
        val cafeMenus = cafeSeriesReaderPort.getCafeMenus(menuIds)
        check(cafeMenus.map { it.id }.toSet() == menuIds.toSet()) {
            throw BusinessException(ErrorCode.CAFE_MENU_INVALID_REQUEST)
        }

        cafeMenus.forEach { cafeMenu ->
            // 1. bulk delete OptionDetails (immediately run query)
            cafeSeriesRemoverPort.bulkDeleteOptionDetailsInBatch(
                cafeMenu.menuOptions.flatMap { menuOption ->
                    menuOption.optionDetails.map { it.id }
                }
            )

            // 2. bulk delete MenuOptions (immediately run query)
            cafeSeriesRemoverPort.bulkDeleteMenuOptionsInBatch(
                menuOptionIds = cafeMenu.menuOptions.map { it.id }
            )
        }

        // 3. bulk delete CafeMenus (after complete bulk deleting MenuOptions, OptionDetails)
        cafeSeriesRemoverPort.bulkDeleteCafeMenusInBatch(cafeMenus.map { it.id })
    }
}
