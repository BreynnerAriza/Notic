package com.notic.task.facade.dtos.response;

import com.notic.taskgroup.facade.dtos.response.TaskGroupResponseDTO;

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