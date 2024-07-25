package com.notic;

import com.notic.token.constants.TokenType;
import com.notic.token.domain.AccessToken;
import com.notic.token.repositories.AccessTokenRepository;
import com.notic.user.domain.User;
import com.notic.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class NoticApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(NoticApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
