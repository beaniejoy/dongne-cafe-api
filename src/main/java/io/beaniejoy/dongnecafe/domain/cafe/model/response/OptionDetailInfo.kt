package io.beaniejoy.dongnecafe.domain.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.entity.OptionDetail
import java.math.BigDecimal

data class OptionDetailInfo(
    val id: Long = 0L,
    val name: String? = null,
    val extra: BigDecimal = BigDecimal.ZERO
) {
    companion object {
        fun of(optionDetail: OptionDetail): OptionDetailInfo {
            return OptionDetailInfo(
                id = optionDetail.id,
                name = optionDetail.name,
                extra = optionDetail.extraPrice
            )
        }
    }
}