package com.notic.task.facade.dtos.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public record TaskCreateDTO(
        String title,
        String description,
        LocalDate expirationDate,
        LocalTime expirationHour
) implements Serializable { }