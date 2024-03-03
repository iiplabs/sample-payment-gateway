package com.iiplabs.spg.web.controllers;

import java.util.Map;
import java.util.stream.Collectors;

import com.iiplabs.spg.web.annotations.RestControllerAnnotation;
import com.iiplabs.spg.web.model.dto.TransactionResponseDto;
import com.iiplabs.spg.web.utils.StringUtil;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = {RestControllerAnnotation.class})
public class CustomValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final HttpEntity<TransactionResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(error -> StringUtil.getLastField(((FieldError) error).getField()),
                        ObjectError::getDefaultMessage));
        return new HttpEntity<>(new TransactionResponseDto(errors));
    }

}
