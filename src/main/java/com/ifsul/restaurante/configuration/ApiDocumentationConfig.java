package com.ifsul.restaurante.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ApiDocumentationConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("IFSUL-RESTAURANTE")
                        .description("API de restaurantes do IFSul!")
                        .version("0.0.1") 
                        .contact(new Contact()
                                .name("Hetiele")
                                .email("hetiellematos86@gmail.com")));
    }
}
