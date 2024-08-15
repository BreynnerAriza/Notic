package com.notic.taskgroup.dtos.request;

import java.io.Serializable;

public record TaskGroupUpdateDTO(
        String name,
        String description,
        String colorIdentifier
) implements Serializable { }
