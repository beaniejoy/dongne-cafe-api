package io.beaniejoy.dongnecafe.common.error.constant

import io.beaniejoy.dongnecafe.common.error.constant.Domain.*
import io.beaniejoy.dongnecafe.common.error.constant.SubCategory.*

enum class ErrorCode(
    val domain: Domain,
    val subCategory: SubCategory
) {
    // COMMON
    COMMON_SERVER_ERROR(COMMON, SERVER_ERROR),

    // AUTH(security 관련)
    AUTH_MEMBER_NOT_FOUND(AUTH, INVALID_AUTHENTICATE_REQUEST),
    AUTH_PASSWORD_NOT_VALID(AUTH, INVALID_AUTHENTICATE_REQUEST),
    AUTH_MEMBER_DEACTIVATED(AUTH, DEACTIVATED),

    // MEMBER
    MEMBER_EXISTED(MEMBER, EXISTED),

    // CAFE
    CAFE_NOT_FOUND(CAFE, NOT_FOUND),
    CAFE_EXISTED(CAFE, EXISTED),

    // CAFE_MENU
    CAFE_MENU_NOT_FOUND(CAFE_MENU, NOT_FOUND),

    // MENU_OPTION
    MENU_OPTION_NOT_FOUND(MENU_OPTION, NOT_FOUND),

    // OPTION_DETAIL
    OPTION_DETAIL_NOT_FOUND(OPTION_DETAIL, NOT_FOUND)

    ;
}