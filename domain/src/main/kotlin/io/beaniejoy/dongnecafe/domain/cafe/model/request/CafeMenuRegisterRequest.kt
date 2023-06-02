package io.beaniejoy.dongnecafe.domain.cafe.model.request

import java.math.BigDecimal

data class CafeMenuRegisterRequest(
    val name: String = "",
    val price: BigDecimal = BigDecimal.ZERO,
    val menuOptions: List<MenuOptionRegisterRequest> = arrayListOf()
)