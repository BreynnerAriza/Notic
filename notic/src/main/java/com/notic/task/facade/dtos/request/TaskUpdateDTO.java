package com.notic.task.facade.dtos.request;

import com.notic.common.validations.annotations.BooleanValidOptional;
import com.notic.common.validations.annotations.NotBlankOptional;
import com.notic.common.validations.annotations.PatternOptional;
import com.notic.common.validations.annotations.SizeOptional;

import java.io.Serializable;

public record TaskUpdateDTO(

        @NotBlankOptional(message = TITLE_TASK_REQUIRED)
        String title,
        @SizeOptional(max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_MAX_LENGTH_INVALID)
        String description,
        @BooleanValidOptional
        Boolean completed,
        @PatternOptional(regexp = EXPIRATION_DATE_PATTERN, message = EXPIRATION_DATE_PATTERN_INVALID)
        String expirationDate,
        @PatternOptional(regexp = EXPIRATION_HOUR_PATTERN, message = EXPIRATION_HOUR_PATTERN_INVALID)
        String expirationHour,
        Integer taskGroupId
) implements Serializable {

    private static final String TITLE_TASK_REQUIRED = "The title of task is required";
    private static final int TITLE_MAX_LENGTH = 50;
    private static final String TITLE_LENGTH_INVALID = "The title cannot contain more than " + TITLE_MAX_LENGTH + " characters";

    private static final int DESCRIPTION_MAX_LENGTH = 1000;
    private static final String DESCRIPTION_MAX_LENGTH_INVALID = "The description cannot be longer than " + DESCRIPTION_MAX_LENGTH + " characters";

    private static final String EXPIRATION_DATE_FORMAT = "dd-MM-yyyy";
    private static final String EXPIRATION_DATE_PATTERN = "^\\d{2}-\\d{2}-\\d{4}$";
    private static final String EXPIRATION_DATE_PATTERN_INVALID = "The expiration date must be in the format " + EXPIRATION_DATE_FORMAT;

    private static final String EXPIRATION_HOUR_FORMAT = "HH:mm";
    private static final String EXPIRATION_HOUR_PATTERN = "^\\d{2}:\\d{2}$";
    private static final String EXPIRATION_HOUR_PATTERN_INVALID = "The expiration hour must be in the format " + EXPIRATION_HOUR_FORMAT;


}

