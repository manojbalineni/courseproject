package com.udemy.learning.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account Services Rest API Documentation",
				description = "Account Related Information",
				version = "v1",
				contact = @Contact(
						name = "Manoj Babu",
						email = "manoj.balineni04@gmail.com",
						url = "https://www.google.com"
				),
				license = @License(
						name = "Apache2.0",
						url = "www.manoj.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Documentation",
				url = "www.google.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
