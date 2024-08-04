package com.notic.auth.dtos.request;

import java.io.Serializable;

public record RefreshTokenDTO (
        String refreshToken
) implements Serializable { }
