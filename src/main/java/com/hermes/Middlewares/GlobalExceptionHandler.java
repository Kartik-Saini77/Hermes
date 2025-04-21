package com.hermes.Middlewares;

import com.hermes.ExceptionHandling.HermesError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("status", HttpStatus.I_AM_A_TEAPOT.value());
//        body.put("error", "I am a Teapot");
//        body.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> errorDetails = new HashMap<>();
                    errorDetails.put("field", error.getField());
                    errorDetails.put("message", error.getDefaultMessage());
                    return errorDetails;
                })
                .collect(Collectors.toList());

        body.put("timestamp", LocalDateTime.now());
        body.put("status", HermesError.BAD_REQUEST);
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
