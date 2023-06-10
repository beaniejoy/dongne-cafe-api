package io.beaniejoy.dongnecafe.cafe.model.request

import java.math.BigDecimal

data class CafeMenuUpdateRequest(
    val name: String? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val menuOptions: List<MenuOptionUpdateRequest> = arrayListOf()
)