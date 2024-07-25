package com.notic.auth.authentication.dtos.request;

import java.io.Serializable;

public record UserRegisterDTO (
    String email,
    String password,
    String names,
    String surnames
) implements Serializable { }
