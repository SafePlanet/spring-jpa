package com.spi.uvschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@ImportResource("classpath:root-context.xml")
public class UvSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(UvSchoolApplication.class, args);
	}
}
