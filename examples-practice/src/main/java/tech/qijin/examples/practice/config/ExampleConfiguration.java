package tech.qijin.examples.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ExampleConfiguration {

    @Profile("dev")
    @Bean
    public RestTemplate rtDev() {
        System.out.println("dev");
        return new RestTemplate();
    }

    @Profile("test")
    @Bean
    public RestTemplate rtTest() {
        System.out.println("test");
        return new RestTemplate();
    }
}
