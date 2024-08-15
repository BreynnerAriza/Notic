package com.notic.taskgroup.dtos.request;

import java.io.Serializable;

public record TaskGroupCreateDTO(
        String name,
        String description,
        String colorIdentifier
) implements Serializable { }
