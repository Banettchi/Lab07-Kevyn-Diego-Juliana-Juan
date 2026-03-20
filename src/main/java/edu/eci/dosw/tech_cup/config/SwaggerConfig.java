package edu.eci.dosw.tech_cup.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TechCup API")
                        .version("1.0.0")
                        .description("Documentación interactiva con Swagger UI - Lab07 DOSW"));
    }
}
