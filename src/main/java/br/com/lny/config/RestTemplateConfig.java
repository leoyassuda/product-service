package br.com.lny.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // Create a bean for restTemplate to call services
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
