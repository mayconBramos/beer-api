package net.atos.beerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("API de Gerenciamento de Cervejas")
						.description("API para gerenciamento de informações sobre cervejas")
						.contact(new Contact().name("Maycon Bruno Ramos")
								.email("maycon.ramos@atos.net"))
						.license(new License().name("123").url("localhost:8080")).version("1.0"));
	}
}
