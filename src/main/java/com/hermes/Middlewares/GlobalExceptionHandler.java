package com.hermes.Middlewares;

import com.hermes.ExceptionHandling.HermesError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HermesError.class)
    public ResponseEntity<Object> handleCustomException(HermesError hermesError, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", hermesError.getStatusCode());
        body.put("error", hermesError.getMessage());

        HttpStatus status = switch (hermesError.getStatusCode()) {
            case HermesError.RESOURCE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case HermesError.BAD_REQUEST -> HttpStatus.BAD_REQUEST;
            case HermesError.FORBIDDEN -> HttpStatus.FORBIDDEN;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.I_AM_A_TEAPOT.value());
        body.put("error", "I am a Teapot");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

