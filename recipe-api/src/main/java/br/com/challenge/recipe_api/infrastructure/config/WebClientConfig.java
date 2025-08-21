package br.com.challenge.recipe_api.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Value("${forkify.api.base-url}")
  private String baseUrl;

  @Bean
  WebClient forkifyWebClient() {
    return WebClient.builder().baseUrl(baseUrl).build();
  }
}
