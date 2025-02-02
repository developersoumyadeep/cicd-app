package com.falconworks.cicd;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cicd")
@SpringBootApplication
public class CiCdApplicationDemo {
    public static void main(String[] args) {
        SpringApplication.run(CiCdApplicationDemo.class, args);
    }

    @Value("${username}")
    private String username;

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello "+username+"! You have successfully deployed this application using cicd";
    }
}
