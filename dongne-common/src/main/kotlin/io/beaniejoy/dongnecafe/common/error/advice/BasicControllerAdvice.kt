package io.beaniejoy.dongnecafe.common.error.advice

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BasicControllerAdvice {

    companion object : KLogging()

    /**
     * 예외 상황 (500 시스템 오류 처리)
     * @param e Exception
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApplicationResponse {
        logger.error { "[COMMON][${e.javaClass.simpleName}] $e" }
        return ApplicationResponse.fail(errorCode = ErrorCode.COMMON_SERVER_ERROR)
    }

    /**
     * 비즈니스 로직 상 에러 처리(예상 가능한 예외 처리)
     * @param e BusinessException
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ApplicationResponse {
        logger.error { "[${BusinessException::class.simpleName}] <ErrorCode>: ${e.errorCode.name}, <ErrorMessage>: ${e.message}" }
        return ApplicationResponse.fail(errorCode = e.errorCode)
    }
}