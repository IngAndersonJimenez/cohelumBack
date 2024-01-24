package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class CohelumApplication extends SpringBootServletInitializer {


    private static Class <CohelumApplication> servletSbsApiFacadeAtm= CohelumApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(servletSbsApiFacadeAtm);
    }

    public static void main(String[] args) {SpringApplication.run(CohelumApplication.class, args);}


}