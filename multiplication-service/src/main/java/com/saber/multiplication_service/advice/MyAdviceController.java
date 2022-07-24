package com.saber.multiplication_service.advice;


import com.saber.multiplication_service.dto.ErrorDto;
import com.saber.multiplication_service.dto.ValidationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class MyAdviceController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ConstraintViolationException ex) {
        log.error("Error for handleResourceNotFoundException with message {}", ex.getMessage());
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(4);
        errorDto.setText(HttpStatus.BAD_REQUEST.getReasonPhrase());
        List<ValidationDto> validationDtoList = new ArrayList<>();
        for (ConstraintViolation<?> c : ex.getConstraintViolations()) {
            ValidationDto validationDto = new ValidationDto();
            validationDto.setFieldName(c.getPropertyPath().toString());
            validationDto.setConstraintMessage(c.getMessage());

            validationDtoList.add(validationDto);
        }
        errorDto.setValidations(validationDtoList);
        log.error("Error for handleResourceNotFoundException with message {}", errorDto);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorDto);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Error for handleMethodArgumentNotValid with message {}", ex.getMessage());

        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(4);
        errorDto.setText(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        errorDto.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}",4,ex.getMessage()));

        List<ValidationDto> validationDtoList = new ArrayList<>();
        for (FieldError f : ex.getFieldErrors()) {
            ValidationDto validationDto = new ValidationDto();
            validationDto.setFieldName(f.getField());
            validationDto.setConstraintMessage(f.getDefaultMessage());

            validationDtoList.add(validationDto);
        }
        errorDto.setValidations(validationDtoList);
        log.error("Error for handleMethodArgumentNotValid with message {}", errorDto);
        return ResponseEntity.status(status).body(errorDto);
    }
}
