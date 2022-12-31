package io.beaniejoy.dongnecafe.common.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode

@JsonInclude(JsonInclude.Include.NON_NULL)
class ApplicationResponse<T>(
    val result: ResultCode,
    val message: String? = null,
    val errorCode: String? = null,
    val data: T? = null
) {
    companion object {
        fun success(message: String? = null): ApplicationResponseBuilder {
            return ApplicationResponseBuilder(
                result = ResultCode.SUCCESS,
                message = message
            )
        }

        fun fail(errorCode: ErrorCode, message: String? = null): ApplicationResponseBuilder {
            return ApplicationResponseBuilder(
                result = ResultCode.FAIL,
                message = message,
                errorCode = errorCode.name
            )
        }
    }
}

class ApplicationResponseBuilder(
    var result: ResultCode,
    var message: String? = null,
    var errorCode: String? = null
) {
    fun build(): ApplicationResponse<Nothing> {
        return data(null)
    }

    fun <T> data(data: T?): ApplicationResponse<T> {
        return ApplicationResponse(
            result = this.result,
            message = this.message,
            data = data,
            errorCode = errorCode
        )
    }

}