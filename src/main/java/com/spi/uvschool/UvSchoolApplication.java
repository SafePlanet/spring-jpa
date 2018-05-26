package com.spi.uvschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UvSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(UvSchoolApplication.class, args);
	}
}
