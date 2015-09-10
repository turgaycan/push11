package com.push11.exception.handler;

import com.push11.exception.custom.ErrorCode;
import com.push11.exception.custom.ErrorResource;
import com.push11.exception.custom.FieldErrorResource;
import com.push11.exception.custom.Push11Exception;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class Push11ExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Push11Exception.class})
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        Push11Exception push11Exception = (Push11Exception) e;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

        if (push11Exception.hasErrors()) {
            for (FieldError fieldError : push11Exception.getFieldErrors()) {

                FieldErrorResource fieldErrorResource = new FieldErrorResource(fieldError.getObjectName(),
                        fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage());

                fieldErrorResources.add(fieldErrorResource);
            }
        } else if (push11Exception.hasErrorCode()) {
            ErrorCode errorCode = push11Exception.getErrorCode();

            FieldErrorResource fieldErrorResource = new FieldErrorResource(errorCode.getType(),
                    StringUtils.EMPTY, String.valueOf(errorCode.getCode()), errorCode.getMessage());

            fieldErrorResources.add(fieldErrorResource);
        }

        ErrorResource error = new ErrorResource("InvalidRequest", push11Exception.getMessage());
        error.setFieldErrors(fieldErrorResources);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}