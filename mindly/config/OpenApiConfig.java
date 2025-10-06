// src/main/java/br/edu/iff/ccc/mindly/config/OpenApiConfig.java
package br.edu.iff.ccc.mindly.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mindly API")
                        .description("API para gerenciamento de usuários, agendamentos e outros recursos do sistema Mindly.") // Descrição detalhada
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe Mindly")
                                .email("contato@mindly.com")
                                .url("http://localhost:8080/"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}