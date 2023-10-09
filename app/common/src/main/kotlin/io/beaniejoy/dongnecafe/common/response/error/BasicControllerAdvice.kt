package io.beaniejoy.dongnecafe.common.response.error

import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.common.error.exception.TokenExpiredException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
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
    fun handleException(e: Exception): ApplicationResponse<Nothing> {
        logger.error { "[COMMON][${e::class.simpleName}]" }
        e.printStackTrace()
        return ApplicationResponse.fail(errorCode = ErrorCode.COMMON_SERVER_ERROR).build()
    }

    /**
     * 비즈니스 로직 상 에러 처리(예상 가능한 예외 처리 - 200 ok 처리)
     * @param e BusinessException
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ApplicationResponse<Nothing> {
        logger.error {
            "[${BusinessException::class.simpleName}] <ErrorCode>: ${e.errorCode.name}, <ErrorMessage>: ${e.message}"
        }
        e.printStackTrace()
        return ApplicationResponse.fail(errorCode = e.errorCode).build()
    }

    /**
     * 인증, 인가 관련 에러 처리
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(*arrayOf(AuthenticationException::class, AccessDeniedException::class))
    fun handleAuthException(e: Exception): ApplicationResponse<Nothing> {
        val errorCode = when (e) {
            is AuthenticationException -> sortErrorCodeByAuthException(e)
            is AccessDeniedException -> ErrorCode.AUTH_ACCESS_DENIED
            else -> ErrorCode.DEFAULT
        }

        logger.error { "[${e::class.simpleName}] <ErrorCode>: ${errorCode.name}, <ErrorMessage>: ${e.message}" }
        e.printStackTrace()
        return ApplicationResponse.fail(errorCode = errorCode).build()
    }

    private fun sortErrorCodeByAuthException(authException: AuthenticationException): ErrorCode {
        if (authException is TokenExpiredException) {
            return ErrorCode.AUTH_TOKEN_EXPIRED
        }

        return ErrorCode.AUTH_UNAUTHORIZED
    }

    // TODO 404 not found에 대한 내용도 api로 처리 필요
}
