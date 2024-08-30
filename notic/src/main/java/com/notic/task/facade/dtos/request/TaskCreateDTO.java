package com.notic.task.facade.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record TaskCreateDTO(

        @NotBlank(message = TITLE_TASK_REQUIRED)
        @Size(max = TITLE_MAX_LENGTH, message = TITLE_LENGTH_INVALID)
        String title,
        @Size(max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_MAX_LENGTH_INVALID)
        String description,
        @NotNull(message = EXPIRATION_DATE_REQUIRED)
        @Pattern(regexp = EXPIRATION_DATE_PATTERN, message = EXPIRATION_DATE_PATTERN_INVALID)
        String expirationDate,
        @Pattern(regexp = EXPIRATION_HOUR_PATTERN, message = EXPIRATION_HOUR_PATTERN_INVALID)
        String expirationHour

) implements Serializable {

    private static final String TITLE_TASK_REQUIRED = "The title of task is required";
    private static final int TITLE_MAX_LENGTH = 50;
    private static final String TITLE_LENGTH_INVALID = "The title cannot contain more than " + TITLE_MAX_LENGTH + " characters";

    private static final int DESCRIPTION_MAX_LENGTH = 1000;
    private static final String DESCRIPTION_MAX_LENGTH_INVALID = "The description cannot be longer than " + DESCRIPTION_MAX_LENGTH + " characters";

    private static final String EXPIRATION_DATE_REQUIRED = "The expiration date is required";
    private static final String EXPIRATION_DATE_FORMAT = "dd-MM-yyyy";
    private static final String EXPIRATION_DATE_PATTERN = "^\\d{2}-\\d{2}-\\d{4}$";
    private static final String EXPIRATION_DATE_PATTERN_INVALID = "The expiration date must be in the format " + EXPIRATION_DATE_FORMAT;

    private static final String EXPIRATION_HOUR_FORMAT = "HH:mm";
    private static final String EXPIRATION_HOUR_PATTERN = "^\\d{2}:\\d{2}$";
    private static final String EXPIRATION_HOUR_PATTERN_INVALID = "The expiration hour must be in the format " + EXPIRATION_HOUR_FORMAT;

}
