package com.notic.user.facade.dtos.response;

public record UserResponseDTO (
        Integer userId,
        String email,
        String names,
        String surnames
){}
