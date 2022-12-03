package io.beaniejoy.dongnecafe.error.handler

import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// TODO 통합된 에러 핸들링 필요(ErrorResponse 규격화)
@RestControllerAdvice
class CommonControllerAdvice {

    companion object : KLogging()

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(e: AuthenticationException): ResponseEntity<ApplicationResponse<Any?>> {
        logger.error { "AuthenticationException: ${e.message}" }
        return ResponseEntity.ok().body(
            ApplicationResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                message = "계정 혹은 비밀번호가 일치하지 않습니다."
            )
        )
    }
}