package com.example.OnlineQuiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class OnlineQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineQuizApplication.class, args);
		System.out.println(" ----->Online quiz system is up and running!<-----");
	}

}