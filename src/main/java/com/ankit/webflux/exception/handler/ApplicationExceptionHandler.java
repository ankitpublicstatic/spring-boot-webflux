package com.ankit.webflux.exception.handler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ankit.webflux.exception.ProductNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<?> handleProductAPIException(ProductNotFoundException exception) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("error message", exception.getMessage());
    errorMap.put("Status", HttpStatus.BAD_REQUEST.toString());
    return ResponseEntity.ok(errorMap);
  }
}
