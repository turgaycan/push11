package com.push11.exception.custom;


import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;

public class Push11Exception extends RuntimeException {
    private static final long serialVersionUID = -6998842778187175542L;

    private Errors errors;
    private ErrorCode errorCode;

    public Push11Exception(String message) {
        super(message);
    }

    public Push11Exception(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Push11Exception(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Push11Exception(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Push11Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Errors getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors != null ? errors.hasErrors() : false;
    }

    public List<FieldError> getFieldErrors() {
        return errors.getFieldErrors();
    }

    public boolean hasErrorCode() {
        return StringUtils.isNotBlank(errorCode.getMessage());
    }
}
