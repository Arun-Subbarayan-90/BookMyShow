package com.scaler.bookmyshow;

import com.scaler.bookmyshow.controllers.UserController;
import com.scaler.bookmyshow.dto.UserSignupRequestDTO;
import jakarta.persistence.EntityListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;
	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		UserSignupRequestDTO userSignupRequestDTO = new UserSignupRequestDTO();
		userSignupRequestDTO.setEmail("arun@scaler.com");
		userSignupRequestDTO.setPassword("password");
		userController.Signup(userSignupRequestDTO);
	}
}
