package com.example.onlinemenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.onlinemenu")
@EntityScan("com.onlinemenu.entity")
@EnableJpaRepositories("com.onlinemenu.repository")



public class OnlineMenuWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineMenuWebApplication.class, args);
    }
}