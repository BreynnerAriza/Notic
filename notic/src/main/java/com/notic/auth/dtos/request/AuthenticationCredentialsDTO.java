package com.notic.auth.dtos.request;

import java.io.Serializable;

public record AuthenticationCredentialsDTO(
        String email,
        String password
) implements Serializable { }
