package com.notic;

import com.notic.auth.authentication.dtos.request.AuthenticationCredentialsDTO;
import com.notic.auth.authentication.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class NoticApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(NoticApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
