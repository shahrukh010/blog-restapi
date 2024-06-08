package com.code.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BlogappApplication {


    public static void main(String[] args) {
        SpringApplication.run(BlogappApplication.class, args);
    }


}
