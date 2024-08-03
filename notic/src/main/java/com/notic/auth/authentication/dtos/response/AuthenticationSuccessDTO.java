package com.notic.auth.authentication.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record AuthenticationSuccessDTO(
    @JsonProperty("access_token")
    String accessToken,
    @JsonProperty("refresh_token")
    String refreshToken
) implements Serializable { }
