package io.beaniejoy.dongnecafe.domain.cafe.dto.request

import java.math.BigDecimal

data class MenuOptionInfoRequestDto(
    val title: String,
    val optionDetailList: List<OptionDetailInfoRequestDto>
)

data class OptionDetailInfoRequestDto(
    val name: String,
    val extraPrice: BigDecimal
)