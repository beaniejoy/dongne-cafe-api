package io.beaniejoy.dongnecafe.common.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode

@JsonInclude(JsonInclude.Include.NON_NULL)
class ApplicationResponse {
    var result: ResultCode
        private set

    var data: Any? = null
        private set

    var message: String?
        private set

    var errorCode: String? = null
        private set

    constructor(resultCode: ResultCode, message: String?) {
        this.result = resultCode
        this.message = message
    }

    constructor(resultCode: ResultCode, errorCode: ErrorCode, message: String?) {
        this.result = resultCode
        this.errorCode = errorCode.name
        this.message = message
    }

    companion object {
        fun success(message: String? = null): ApplicationResponse {
            return ApplicationResponse(resultCode = ResultCode.SUCCESS, message = message)
        }

        fun fail(errorCode: ErrorCode, message: String? = null): ApplicationResponse {
            return ApplicationResponse(
                resultCode = ResultCode.FAIL,
                errorCode = errorCode,
                message = message
            )
        }
    }

    fun data(data: Any): ApplicationResponse {
        this.data = data

        return this
    }
}