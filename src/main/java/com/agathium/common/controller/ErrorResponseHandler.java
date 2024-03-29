package com.agathium.common.controller;

import com.agathium.common.exception.EntityNotFoundException;
import com.agathium.common.exception.Errors;
import com.agathium.common.exception.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.lang.String.format;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 23:28
 */
@ControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Errors errors = new Errors();
        for (FieldError fieldError : fieldErrors) {
            errors.getErrors().add(format("%s %s", fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ResponseEntity(errors, headers, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Errors> handleValidationException(EntityNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new Errors(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<?> handleValidationException(ValidationException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<?> handleValidationException(IllegalArgumentException ex, WebRequest request) {
        ResponseEntity responseEntity = null;
        if (ex.getMessage() != null && ex.getMessage().contains("invalid hexadecimal representation of an ObjectId")) {
            responseEntity =  new ResponseEntity<>(new Errors("Invalid id"), HttpStatus.BAD_REQUEST);
        } else {
            responseEntity =  new ResponseEntity<>(new Errors(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
