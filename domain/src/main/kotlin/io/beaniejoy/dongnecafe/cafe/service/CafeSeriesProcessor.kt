package io.beaniejoy.dongnecafe.cafe.service

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.cafe.model.CafeCommand

interface CafeSeriesProcessor {
    fun bulkSaveCafeMenuSeries(cafeMenu: CafeMenu, commands: List<CafeCommand.RegisterMenuOption>)

    fun bulkDeleteCafeMenuSeries(commands: List<CafeCommand.UpdateMenuOption>)

    fun bulkDeleteCafeMenus(menuIds: List<Long>)
}