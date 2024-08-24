package com.notic.taskgroup.facade.dtos.response;

import java.io.Serializable;

public record TaskGroupResponseDTO(
        Integer taskGroupId,
        String name,
        String description,
        String colorIdentifier
) implements Serializable { }
