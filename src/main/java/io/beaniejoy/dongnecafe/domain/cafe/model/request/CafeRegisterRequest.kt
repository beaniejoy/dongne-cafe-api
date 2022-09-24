package io.beaniejoy.dongnecafe.domain.cafe.model.request

data class CafeRegisterRequest(
    val name: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val description: String? = null,
    val cafeMenuList: List<CafeMenuRegisterRequest> = arrayListOf()
)