package io.beaniejoy.dongnecafe.domain.cafe.model.request

import java.math.BigDecimal

data class MenuOptionRegisterRequest(
    val title: String,
    val optionDetails: List<OptionDetailRegisterRequest> = arrayListOf()
)

data class OptionDetailRegisterRequest(
    val name: String,
    val extraPrice: BigDecimal
)