package com.acid.myddd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.acid.myddd")
@MapperScan("com.acid.myddd.user.infrastructure.persistence.mapper")
public class MyDddApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDddApplication.class, args);
    }

}
