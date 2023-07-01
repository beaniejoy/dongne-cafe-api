package io.beaniejoy.dongnecafe.cafe.service.impl

import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesReaderPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesRemoverPort
import io.beaniejoy.dongnecafe.cafe.service.CafeSeriesProcessor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeSeriesProcessorImpl(
    private val cafeSeriesReaderPort: CafeSeriesReaderPort,
    private val cafeSeriesRemoverPort: CafeSeriesRemoverPort
) : CafeSeriesProcessor {
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