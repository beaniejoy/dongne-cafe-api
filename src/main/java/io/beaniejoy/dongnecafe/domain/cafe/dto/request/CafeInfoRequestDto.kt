package io.beaniejoy.dongnecafe.domain.cafe.dto.request

data class CafeInfoRequestDto(
    val name: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val description: String? = null,
    val cafeMenuList: List<CafeMenuInfoRequestDto> = arrayListOf()
)