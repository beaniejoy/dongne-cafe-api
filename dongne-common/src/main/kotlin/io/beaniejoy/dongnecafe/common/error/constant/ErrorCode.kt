package io.beaniejoy.dongnecafe.common.error.constant

import io.beaniejoy.dongnecafe.common.error.constant.Domain.*
import io.beaniejoy.dongnecafe.common.error.constant.SubCategory.*

enum class ErrorCode(
    val domain: Domain,
    val subCategory: SubCategory
) {
    // AUTH(security 관련)
    AUTH_COMMON_EXCEPTION(AUTH, COMMON),
    AUTH_MEMBER_NOT_FOUND(AUTH, INVALID_AUTHENTICATE_REQUEST),
    AUTH_PASSWORD_NOT_VALID(AUTH, INVALID_AUTHENTICATE_REQUEST),
    AUTH_MEMBER_DEACTIVATED(AUTH, DEACTIVATED),

    // MEMBER
    MEMBER_EXISTED(MEMBER, EXISTED),

    // CAFE
    CAFE_NOT_FOUND(CAFE, NOT_FOUND),
    CAFE_EXISTED(CAFE, EXISTED);

    companion object {
        fun convertOrNull(value: String?): ErrorCode? {
            return values().find { it.name === value }
        }
    }
}