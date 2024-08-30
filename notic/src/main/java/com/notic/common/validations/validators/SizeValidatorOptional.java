package com.notic.common.validations.validators;

import com.notic.common.validations.annotations.SizeOptional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SizeValidatorOptional implements ConstraintValidator<SizeOptional, String> {

    private int min;
    private int max;


    @Override
    public void initialize(SizeOptional constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || (value.trim().length() >= min && value.trim().length() <= max);
    }

}
