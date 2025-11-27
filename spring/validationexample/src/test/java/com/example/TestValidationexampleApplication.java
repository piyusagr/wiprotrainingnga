package com.example;

import org.springframework.boot.SpringApplication;

public class TestValidationexampleApplication {

	public static void main(String[] args) {
		SpringApplication.from(ValidationexampleApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
