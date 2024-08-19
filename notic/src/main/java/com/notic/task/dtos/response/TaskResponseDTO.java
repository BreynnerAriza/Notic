package com.notic.task.dtos.response;

import com.notic.taskgroup.dtos.response.TaskGroupResponseDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TaskResponseDTO(
        Integer taskId,
        String title,
        String description,
        Boolean completed,
        LocalDateTime creationDate,
        LocalDate expirationDate,
        LocalTime expirationHour,
        TaskGroupResponseDTO taskGroup
) implements Serializable { }