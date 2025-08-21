package br.com.challenge.recipe_api.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Recipe Search API")
            .version("1.0.0")
            .description("API que atua como um proxy para o serviço da Forkify, permitindo a busca por receitas.")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }
}
