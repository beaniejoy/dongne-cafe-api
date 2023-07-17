package io.beaniejoy.dongnecafe.domain.cafe.persistence

interface CafeSeriesRemoverPort {
    fun bulkDeleteCafeMenusInBatch(cafeMenuIds: List<Long>)
    fun bulkDeleteMenuOptionsInBatch(menuOptionIds: List<Long>)
    fun bulkDeleteOptionDetailsInBatch(optionDetailIds: List<Long>)
}
