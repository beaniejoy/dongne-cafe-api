package io.beaniejoy.dongnecafe.domain.cafe.model.request

import java.math.BigDecimal

data class CafeMenuUpdateRequest(
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val menuOptionList: List<MenuOptionUpdateRequest> = arrayListOf()
)