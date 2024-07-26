package com.notic.auth.authentication.dtos.request;

import java.io.Serializable;

public record AuthenticationCredentialsDTO(
        String email,
        String password
) implements Serializable { }
