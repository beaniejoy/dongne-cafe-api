package io.beaniejoy.dongnecafe.common.response.error

import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
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
     * - TODO: 우선 인증, 인가 관련 에러도 여기에 포함 - 분리할지 고민
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
     * - TODO: 이부분을 따로 구성해야할 필요성 아직 모르겠음
     * - 현재로써는 BusinessException handler 내용과 똑같다고 볼 수 있지만 성격상 분리
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(*arrayOf(AuthenticationException::class, AccessDeniedException::class))
    fun handleAuthException(e: Exception): ApplicationResponse<Nothing> {
        val errorCode = when (e) {
            is AuthenticationException -> ErrorCode.AUTH_UNAUTHORIZED
            is AccessDeniedException -> ErrorCode.AUTH_ACCESS_DENIED
            else -> ErrorCode.DEFAULT
        }

        logger.error { "[${e::class.simpleName}] <ErrorCode>: ${errorCode.name}, <ErrorMessage>: ${e.message}" }
        e.printStackTrace()
        return ApplicationResponse.fail(errorCode = errorCode).build()
    }

    // TODO 404 not found에 대한 내용도 api로 처리 필요
}
