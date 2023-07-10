package io.beaniejoy.dongnecafe.cafe.persistence

interface CafeSeriesRemoverPort {
    fun bulkDeleteCafeMenusInBatch(cafeMenuIds: List<Long>)
    fun bulkDeleteMenuOptionsInBatch(menuOptionIds: List<Long>)
    fun bulkDeleteOptionDetailsInBatch(optionDetailIds: List<Long>)
}