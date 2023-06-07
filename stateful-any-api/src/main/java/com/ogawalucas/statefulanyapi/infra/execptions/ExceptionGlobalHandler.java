package com.ogawalucas.statefulanyapi.infra.execptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex) {
        return ex.getMessage();
    }
}
