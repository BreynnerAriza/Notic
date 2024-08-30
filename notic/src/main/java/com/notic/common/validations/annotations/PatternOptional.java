package com.notic.common.validations.annotations;

import com.notic.common.validations.validators.PatternValidatorOptional;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = PatternValidatorOptional.class)
public @interface PatternOptional {

    String message() default "{jakarta.validation.constraints.Pattern.message}";
    String regexp();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
