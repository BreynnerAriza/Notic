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


	}


}
