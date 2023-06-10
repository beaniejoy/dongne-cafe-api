package io.beaniejoy.dongnecafe.cafe.model.response

import io.beaniejoy.dongnecafe.cafe.entity.OptionDetail
import java.math.BigDecimal

data class OptionDetailInfo(
    val optionDetailId: Long = 0L,
    val name: String? = null,
    val extra: BigDecimal = BigDecimal.ZERO
) {
    companion object {
        fun of(optionDetail: OptionDetail): OptionDetailInfo {
            return OptionDetailInfo(
                optionDetailId = optionDetail.id,
                name = optionDetail.name,
                extra = optionDetail.extraPrice
            )
        }
    }
}