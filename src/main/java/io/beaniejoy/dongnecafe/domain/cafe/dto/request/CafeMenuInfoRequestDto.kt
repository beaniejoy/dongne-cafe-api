package io.beaniejoy.dongnecafe.domain.cafe.dto.request

import java.math.BigDecimal

data class CafeMenuInfoRequestDto(
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val menuOptionList: List<MenuOptionInfoRequestDto>
)
