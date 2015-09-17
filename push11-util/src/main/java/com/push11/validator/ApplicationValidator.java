package com.push11.validator;

import com.push11.domain.document.Application;
import com.push11.exception.custom.ErrorCode;
import com.push11.exception.custom.Push11Exception;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ApplicationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Application.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        //ValidationUtils.rejectIfEmpty(errors, "", "");
        //Add custom validations..
//        Application application = (Application) o;
        ValidationUtils.rejectIfEmpty(errors, "applicationId", "ID is required field..");

        if(errors.hasErrors()){
            try {
                throw new Push11Exception("field", ErrorCode.UNKNOWN_EXCEPTION);
            } catch (Push11Exception e) {
                //e.printStackTrace();
            }
        }
    }
}
