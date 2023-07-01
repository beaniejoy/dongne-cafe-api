package io.beaniejoy.dongnecafe.cafe.service

import io.beaniejoy.dongnecafe.cafe.model.CafeCommand

interface CafeSeriesProcessor {
    fun bulkDeleteCafeMenuSeries(commands: List<CafeCommand.UpdateMenuOption>)

    fun bulkDeleteCafeMenus(menuIds: List<Long>)
}