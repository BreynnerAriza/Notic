package com.notic.common.validations.validators;

import com.notic.common.validations.annotations.PatternOptional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PatternValidatorOptional implements ConstraintValidator<PatternOptional, String> {

    private String regexp;

    @Override
    public void initialize(PatternOptional constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.trim().matches(regexp);
    }

}
