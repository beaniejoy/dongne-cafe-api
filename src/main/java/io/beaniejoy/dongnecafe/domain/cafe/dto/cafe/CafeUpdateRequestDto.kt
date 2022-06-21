package io.beaniejoy.dongnecafe.domain.cafe.dto.cafe

data class CafeUpdateRequestDto(
    val name: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val description: String? = null
)