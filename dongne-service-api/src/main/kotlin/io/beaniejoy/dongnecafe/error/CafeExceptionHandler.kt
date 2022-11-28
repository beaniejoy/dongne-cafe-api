package io.beaniejoy.dongnecafe.error

import io.beaniejoy.dongnecafe.error.exception.CafeNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CafeExceptionHandler {
    // TODO: error 규격화
    @ExceptionHandler(CafeNotFoundException::class)
    fun handleNotFound(exception: CafeNotFoundException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(exception.message)
    }
}