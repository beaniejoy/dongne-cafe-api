package io.beaniejoy.dongnecafe.domain.cafe.model

class CafeQuery {
    data class SearchCafesParam(
        val name: String?,
        val address: String?
    )
}
