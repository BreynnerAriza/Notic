package com.notic.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpHeaders;


@OpenAPIDefinition(
    info = @Info(
            title = "Notic API REST",
            description = "Notic documentation for its correct use",
            version = "1.0",
            contact = @Contact(
                    name = "Breynner Ariza",
                    email = "breynner.fabian.ariza.florez@gmail.com",
                    url = "www.linkedin.com/in/breynner-ariza"
            )
    )
)
@SecurityScheme(
    name = "Security Token",
    description = "Access token for my API",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER,
    paramName = HttpHeaders.AUTHORIZATION,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig { }
