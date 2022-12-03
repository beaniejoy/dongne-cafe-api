package io.beaniejoy.dongnecafe.common.error.constant

import io.beaniejoy.dongnecafe.common.error.constant.Domain.CAFE
import io.beaniejoy.dongnecafe.common.error.constant.SubCategory.*

enum class ErrorCode(
    val domain: Domain,
    val subCategory: SubCategory
) {
    // CAFE
    CAFE_NOT_FOUND(CAFE, NOT_FOUND),
    CAFE_EXISTED(CAFE, EXISTED);
}