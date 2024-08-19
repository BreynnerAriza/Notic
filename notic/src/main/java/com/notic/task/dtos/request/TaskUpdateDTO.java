package com.notic.task.dtos.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public record TaskUpdateDTO(
        String title,
        String description,
        Boolean completed,
        LocalDate expirationDate,
        LocalTime expirationHour,
        Integer taskGroupId
) implements Serializable { }

