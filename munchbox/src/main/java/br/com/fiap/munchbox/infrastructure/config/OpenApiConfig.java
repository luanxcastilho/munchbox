package br.com.fiap.munchbox.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;

@OpenAPIDefinition
@ControllerAdvice
public class OpenApiConfig {

    @Bean
    public OpenAPI munchbox() {
        return new OpenAPI()
                .info(new Info()
                        .title("MunchBox API")
                        .version("1.0")
                        .description("Aplicação de restaurante criada para a pós graduação de arquitetura e desenvolvimento Java.")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/luanxcastilho")));
    }
}
