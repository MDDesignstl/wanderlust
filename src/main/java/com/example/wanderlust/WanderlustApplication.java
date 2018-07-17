package com.example.wanderlust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class WanderlustApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.livereload.enabled","true");
		System.setProperty("spring.thymeleaf.cache", "false");
		SpringApplication.run(WanderlustApplication.class, args);
	}
}

//TODO: implement favorites page