package io.beaniejoy.dongnecafe.domain.cafe.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CafeExceptionHandler {

    @ExceptionHandler(CafeNotFoundException.class)
    public ResponseEntity<String> handleNotFound(CafeNotFoundException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
