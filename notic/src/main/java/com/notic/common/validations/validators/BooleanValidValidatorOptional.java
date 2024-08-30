package com.notic.common.validations.validators;

import com.notic.common.validations.annotations.BooleanValidOptional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BooleanValidValidatorOptional implements ConstraintValidator<BooleanValidOptional, Boolean> {

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return value == null || value instanceof Boolean;
    }

}
