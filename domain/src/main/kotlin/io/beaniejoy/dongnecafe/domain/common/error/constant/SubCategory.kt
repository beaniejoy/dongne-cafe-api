package io.beaniejoy.dongnecafe.domain.common.error.constant

enum class SubCategory {
    SERVER_ERROR,

    // Client Side Exception
    NOT_FOUND,
    EXISTED,
    INVALID_REQUEST,

    INVALID_AUTHENTICATE_REQUEST,
    DEACTIVATED,
    ACCESS_DENIED,
    UNAUTHORIZED,
    EXPIRED,
    NOT_EXPIRED
}
