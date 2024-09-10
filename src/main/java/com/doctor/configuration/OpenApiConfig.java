package com.doctor.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI openAPIPlugin() {
        return new OpenAPI().info(apiInfo());
    }


    private Info apiInfo() {
        Contact contact = new Contact();
        contact.setEmail("contact@Demo.com");
        contact.setName("Swagger");
        String Url = "https://www.demo.com";
        contact.setUrl(Url);

        License license = new License().name("Swagger")
                .url(Url);

        return new Info()
                .title("Swagger")
                .version("1.0")
                .contact(contact)
                .description("Swagger").termsOfService(Url)
                .license(license);
    }

}
