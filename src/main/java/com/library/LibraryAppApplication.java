package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LibraryAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryAppApplication.class, args);
    }
}