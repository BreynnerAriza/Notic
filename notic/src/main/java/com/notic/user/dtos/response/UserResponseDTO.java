package com.notic.user.dtos.response;

public record UserResponseDTO (
        Integer userId,
        String email,
        String names,
        String surnames
){}
