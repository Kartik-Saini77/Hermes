package com.hermes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.hermes.DbCore")
@EnableAsync
public class HermesApplication {
    public static void main(String[] args) {
        SpringApplication.run(HermesApplication.class, args);
    }
}