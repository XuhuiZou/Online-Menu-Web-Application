package com.onlinemenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.persistence.autoconfigure.domain.EntityScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.onlinemenu", "com.example.onlinemenu"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = OnlineMenuWebApplication.JpaConfig.class))
public class OnlineMenuWebApplication {

    @Configuration
    @EnableJpaRepositories(basePackages = "com.example.onlinemenu.repository")
    @EntityScan(basePackages = "com.example.onlinemenu.entity")
    static class JpaConfig {
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineMenuWebApplication.class, args);
    }

}
