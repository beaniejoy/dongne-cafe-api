package io.beaniejoy.dongnecafe.domain.cafe.dto.menu

import io.beaniejoy.dongnecafe.domain.cafe.domain.OptionDetail
import java.math.BigDecimal

data class OptionDetailResponseDto(
    val id: Long = 0L,
    val name: String? = null,
    val extra: BigDecimal = BigDecimal.ZERO
) {
    companion object {
        fun of(optionDetail: OptionDetail): OptionDetailResponseDto {
            return OptionDetailResponseDto(
                id = optionDetail.id,
                name = optionDetail.name,
                extra = optionDetail.extra
            )
        }
    }
}