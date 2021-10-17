package com.sszkoluda.github.userinfoapi.rest.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<Object> handleNotFoundException(HttpClientErrorException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), ex.getStatusCode());
    }
}
