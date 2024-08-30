package com.notic.taskgroup.facade.dtos.request;

import com.notic.common.validations.annotations.NotBlankOptional;
import com.notic.common.validations.annotations.SizeOptional;

import java.io.Serializable;

public record TaskGroupUpdateDTO(
        @NotBlankOptional(message = NAME_REQUIRED)
        @SizeOptional(max = NAME_MAX_LENGTH, message = NAME_MAX_LENGTH_INVALID)
        String name,
        @SizeOptional(max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_MAX_LENGTH_INVALID)
        String description,
        @SizeOptional(max = COLOR_IDENTIFIER_MAX_LENGTH, message = COLOR_IDENTIFIER_MAX_LENGTH_INVALID)
        String colorIdentifier
) implements Serializable {

        private static final String NAME_REQUIRED = "The name of task group is required";
        private static final int NAME_MAX_LENGTH = 50;
        private static final String NAME_MAX_LENGTH_INVALID = "The name cannot be longer than " + NAME_MAX_LENGTH + " characters";

        private static final int DESCRIPTION_MAX_LENGTH = 1000;
        private static final String DESCRIPTION_MAX_LENGTH_INVALID = "The description cannot be longer than " + DESCRIPTION_MAX_LENGTH + " characters";

        private static final int COLOR_IDENTIFIER_MAX_LENGTH = 7;
        private static final String COLOR_IDENTIFIER_MAX_LENGTH_INVALID = "The color identifier cannot be longer than " + COLOR_IDENTIFIER_MAX_LENGTH + " characters";

}
