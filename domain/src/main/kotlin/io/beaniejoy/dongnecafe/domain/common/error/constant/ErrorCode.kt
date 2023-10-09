package io.beaniejoy.dongnecafe.domain.common.error.constant

import io.beaniejoy.dongnecafe.domain.common.error.constant.Domain.*
import io.beaniejoy.dongnecafe.domain.common.error.constant.SubCategory.*

enum class ErrorCode(
    val domain: Domain,
    val subCategory: SubCategory?
) {
    // COMMON
    COMMON_SERVER_ERROR(COMMON, SERVER_ERROR),
    DEFAULT(COMMON, null),

    // AUTH(security 관련)
    AUTH_MEMBER_NOT_FOUND(AUTH, INVALID_AUTHENTICATE_REQUEST),
    AUTH_PASSWORD_NOT_VALID(AUTH, INVALID_AUTHENTICATE_REQUEST),
    AUTH_MEMBER_DEACTIVATED(AUTH, DEACTIVATED),
    AUTH_ACCESS_DENIED(AUTH, ACCESS_DENIED),
    AUTH_UNAUTHORIZED(AUTH, UNAUTHORIZED),
    AUTH_TOKEN_INVALID_REQUEST(AUTH, INVALID_REQUEST),
    AUTH_TOKEN_EXPIRED(AUTH, EXPIRED),

    // MEMBER
    MEMBER_EXISTED(MEMBER, EXISTED),

    // CAFE
    CAFE_INVALID_REQUEST(CAFE, INVALID_REQUEST),
    CAFE_NOT_FOUND(CAFE, NOT_FOUND),
    CAFE_EXISTED(CAFE, EXISTED),

    // CAFE_MENU_CATEGORY
    CAFE_MENU_CATEGORY_NOT_FOUND(CAFE_MENU_CATEGORY, NOT_FOUND),
    CAFE_MENU_CATEGORY_EXISTED(CAFE_MENU_CATEGORY, EXISTED),
    CAFE_MENU_CATEGORY_INVALID_REQUEST(CAFE_MENU_CATEGORY, INVALID_REQUEST),

    // CAFE_MENU
    CAFE_MENU_NOT_FOUND(CAFE_MENU, NOT_FOUND),
    CAFE_MENU_EXISTED(CAFE_MENU, EXISTED),
    CAFE_MENU_INVALID_REQUEST(CAFE_MENU, INVALID_REQUEST),

    // MENU_OPTION
    MENU_OPTION_NOT_FOUND(MENU_OPTION, NOT_FOUND),
    MENU_OPTION_INVALID_REQUEST(MENU_OPTION, INVALID_REQUEST),

    // OPTION_DETAIL
    OPTION_DETAIL_NOT_FOUND(OPTION_DETAIL, NOT_FOUND),
    OPTION_DETAIL_INVALID_REQUEST(OPTION_DETAIL, INVALID_REQUEST)
}
