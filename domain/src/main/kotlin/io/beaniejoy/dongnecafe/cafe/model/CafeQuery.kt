package io.beaniejoy.dongnecafe.cafe.model

class CafeQuery {
    data class SearchCafesParam(
        val name: String?,
        val address: String?
    )
}