package io.beaniejoy.dongnecafe.domain.cafe.model.request

import java.math.BigDecimal

data class MenuOptionUpdateRequest(
    val menuOptionId: Long,
    val title: String,
    val isDelete: Boolean = false,
    val optionDetailList: List<OptionDetailUpdateRequest> = arrayListOf()
)

data class OptionDetailUpdateRequest(
    val optionDetailId: Long,
    val name: String,
    val extraPrice: BigDecimal,
    val isDelete: Boolean = false
)