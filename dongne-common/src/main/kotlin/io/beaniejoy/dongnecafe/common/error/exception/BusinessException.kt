package io.beaniejoy.dongnecafe.common.error.exception

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode

/*
* Business Logic 상 발생 가능한 Exception
* - 로직상 개발자가 예측 가능한 예외
* - front 측면에서 해당 에러에 대해서 error code(4xx, 5xx)보다 success code(2xx)를 응답받게 설계
* - front에서 해당 예외 응답에 대해서 ErrorResponse의 Result field로 따로 구분해서 처리가능
*/
class BusinessException : RuntimeException {
    var errorCode: ErrorCode
        private set

    constructor(errorCode: ErrorCode) : super(errorCode.name){
        this.errorCode = errorCode
    }

    constructor(errorCode: ErrorCode, message: String): super(message) {
        this.errorCode = errorCode
    }

    constructor(errorCode: ErrorCode, cause: Throwable) : super(cause) {
        this.errorCode = errorCode
    }

    constructor(errorCode: ErrorCode, message: String, cause: Throwable) : super(message, cause) {
        this.errorCode = errorCode
    }
}