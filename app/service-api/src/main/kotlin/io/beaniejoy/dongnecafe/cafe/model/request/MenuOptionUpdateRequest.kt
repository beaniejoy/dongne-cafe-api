package io.beaniejoy.dongnecafe.cafe.model.request

import java.math.BigDecimal

data class MenuOptionUpdateRequest(
    val menuOptionId: Long,
    val title: String,
    val isDelete: Boolean = false,
    val optionDetails: List<OptionDetailUpdateRequest> = arrayListOf()
)

data class OptionDetailUpdateRequest(
    val optionDetailId: Long,
    val name: String,
    val extraPrice: BigDecimal,
    val isDelete: Boolean = false
)