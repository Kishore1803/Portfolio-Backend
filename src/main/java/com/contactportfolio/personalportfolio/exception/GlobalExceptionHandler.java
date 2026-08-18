package com.contactportfolio.personalportfolio.exception;

import com.contactportfolio.personalportfolio.dto.ContactResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ContactResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();

        ContactResponse response = new ContactResponse();
        response.setStatus("FAILED");
        response.setMessage(errorMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // All Other Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ContactResponse> handleException(Exception ex) {

        ContactResponse response = new ContactResponse();
        response.setStatus("ERROR");
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}