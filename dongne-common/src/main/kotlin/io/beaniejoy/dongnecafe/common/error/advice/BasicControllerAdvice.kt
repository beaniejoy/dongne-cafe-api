package io.beaniejoy.dongnecafe.common.error.advice

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

    // 비즈니스 로직 상 에러 처리
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ApplicationResponse {
        logger.error { "[${BusinessException::class.simpleName}], <ErrorCode>: ${e.errorCode.name}, <ErrorMessage>: ${e.message}" }
        return ApplicationResponse.fail(errorCode = e.errorCode)
    }
}