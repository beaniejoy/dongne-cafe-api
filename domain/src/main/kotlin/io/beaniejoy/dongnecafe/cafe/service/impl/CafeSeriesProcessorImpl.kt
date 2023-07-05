package io.beaniejoy.dongnecafe.cafe.service.impl

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.cafe.entity.MenuOption
import io.beaniejoy.dongnecafe.cafe.entity.OptionDetail
import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesReaderPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesRemoverPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeStorePort
import io.beaniejoy.dongnecafe.cafe.service.CafeSeriesProcessor
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
    override fun bulkDeleteCafeMenuSeries(commands: List<CafeCommand.UpdateMenuOption>) {
        // 1. bulk delete OptionDetails
        val deleteOptionDetailIds = commands.flatMap { command ->
            command.optionDetails
                .filter { it.delete }
                .map { it.optionDetailId }
        }

        cafeSeriesRemoverPort.bulkDeleteOptionDetails(optionDetailIds = deleteOptionDetailIds)

        // 2. bulk delete MenuOptions
        val deleteMenuOptionIds = commands.filter { it.delete }.map { it.menuOptionId }

        cafeSeriesRemoverPort.bulkDeleteMenuOptions(menuOptionIds = deleteMenuOptionIds)
    }

    override fun bulkDeleteCafeMenus(menuIds: List<Long>) {
        val cafeMenus = cafeSeriesReaderPort.getCafeMenus(menuIds)

        cafeMenus.forEach { cafeMenu ->
            cafeSeriesRemoverPort.bulkDeleteOptionDetails(
                optionDetailIds = cafeMenu.menuOptions.flatMap { menuOption ->
                    menuOption.optionDetails.map { it.id }
                }
            )

            cafeSeriesRemoverPort.bulkDeleteMenuOptions(
                menuOptionIds = cafeMenu.menuOptions.map { it.id }
            )
        }
    }
}