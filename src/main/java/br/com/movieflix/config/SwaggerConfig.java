package br.com.movieflix.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "bearerAuth";

        Contact contact = new Contact()
                .email("andresacon2@gmail.com")
                .name("André Sacon");

        License license = new License()
                .name("Copyright © 2025 André Sacon")
                .url("https://github.com/Gularte-png/MovieFlix.git");

        Info info = new Info()
                .title("MovieFlix API")
                .description("API de cadastro e gerenciamento de filmes e streaming")
                .version("1.0")
                .contact(contact)
                .license(license);

        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Insira o token JWT no formato: **Bearer {seu_token}**");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(securitySchemeName);

        Components components = new Components()
                .addSecuritySchemes(securitySchemeName, securityScheme);

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
