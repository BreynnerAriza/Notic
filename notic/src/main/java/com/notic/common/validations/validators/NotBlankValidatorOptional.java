package com.notic.common.validations.validators;

import com.notic.common.validations.annotations.NotBlankOptional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankValidatorOptional implements ConstraintValidator<NotBlankOptional, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !value.isBlank();
    }

}
