package com.push11.validator;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.RegexValidator;

public class Push11RegexValidator {

    public boolean isValid(String regex, String value) {
        return new RegexValidator(regex).isValid(value);
    }

    public boolean isNotValid(String regex, String value) {
        return !isValid(regex, value);
    }

    public String[] matches(String regex, String value) {
        return StringUtils.split(value, regex);
    }

}
