package com.notic.auth.facade.dtos.request;

import java.io.Serializable;

public record RefreshTokenDTO (
        String refreshToken
) implements Serializable { }
