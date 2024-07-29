package com.notic;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.notic.common.security.services.JwtService;
import com.notic.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NoticApplication implements CommandLineRunner {

	@Autowired
	private JwtService jwtService;
	public static void main(String[] args) {
		SpringApplication.run(NoticApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = User.builder()
				.email("Breynner Ariza")
				.build();
		String token = jwtService.getToken(user);
		System.out.println("token = " + token);
		DecodedJWT decodedJWT =
				jwtService
						.decodeToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBVVRIMFVTRVIiLCJzdWIiOiJCcmV5bm5lciBBcml6YSIsImp0aSI6IjMwMWI0OGJmLWFkMzgtNGYzNC1iYjliLWQ4OWI3YWFlYWQ1NSIsImlhdCI6MTcyMjI2ODk0OCwiZXhwIjoxNzIyMjY4OTc4LCJuYmYiOjE3MjIyNjg5NDh9.WNQFwH-9rR6OsV-gpOiijCby-5M7wRhYjFAirVdaKeI");


		System.out.println("Se decodifico correctamente");
	}


}
