package io.beaniejoy.dongnecafe.domain.cafe.model.request

data class CafeMenuBulkDeleteRequest(
    val cafeMenuIds: List<Long>
)