package com.fridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Fridge Swagger API")
				.description("구미 9팀 Spring Boot Swagger")
				.version("v0.0.1")
				.license(new License().name("Apache 2.0").url("http://spring.org")))
				.externalDocs(new ExternalDocumentation()
						.description("SpringShop Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"))
				.components(new Components()
				          .addSecuritySchemes("X-AUTH-TOKEN",
				                  new SecurityScheme().name("X-AUTH-TOKEN").type(SecurityScheme.Type.APIKEY).in(In.HEADER)));
	}
}
