package com.enotes_service.enoteserviceapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditAware")
@SpringBootApplication
public class EnoteserviceapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnoteserviceapisApplication.class, args);
	}

}
