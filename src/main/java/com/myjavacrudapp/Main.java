package com.myjavacrudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.myjavacrudapp.model")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}