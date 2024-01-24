package com.example.apacheignitedemo;

import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableIgniteRepositories
@SpringBootApplication
public class ApacheIgniteDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheIgniteDemoApplication.class, args);
    }

}
