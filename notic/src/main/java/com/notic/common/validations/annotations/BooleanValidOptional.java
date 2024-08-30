package com.notic.common.validations.annotations;

import com.notic.common.validations.validators.BooleanValidValidatorOptional;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = BooleanValidValidatorOptional.class)
public @interface BooleanValidOptional {

    String message() default "The value must be 'true' or 'false' to be convertible to boolean";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
