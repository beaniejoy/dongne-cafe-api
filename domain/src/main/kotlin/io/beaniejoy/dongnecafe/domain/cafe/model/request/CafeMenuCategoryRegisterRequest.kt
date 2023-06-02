package io.beaniejoy.dongnecafe.domain.cafe.model.request

data class CafeMenuCategoryRegisterRequest(
    val name: String,
    val description: String,
    val cafeMenus: List<CafeMenuRegisterRequest> = arrayListOf()
)