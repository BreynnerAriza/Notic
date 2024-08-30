package com.notic.common.validations.annotations;

import com.notic.common.validations.validators.SizeValidatorOptional;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = SizeValidatorOptional.class)
public @interface SizeOptional {


    String message() default "{jakarta.validation.constraints.Size.message}";

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
