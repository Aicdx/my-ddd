package com.acid.myddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.acid.myddd")
public class MyDddApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDddApplication.class, args);
    }

}
