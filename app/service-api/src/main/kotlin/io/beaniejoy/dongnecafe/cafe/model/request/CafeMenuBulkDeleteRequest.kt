package io.beaniejoy.dongnecafe.cafe.model.request

data class CafeMenuBulkDeleteRequest(
    val cafeMenuIds: List<Long>
)