package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CohelumApplication extends SpringBootServletInitializer {

    private static Class<CohelumApplication> cohelumApplicationClass = CohelumApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(cohelumApplicationClass);
    }

    public static void main(String[] args) {
        SpringApplication.run(CohelumApplication.class, args);
    }
}