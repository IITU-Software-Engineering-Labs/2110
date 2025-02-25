package com.example.dokerdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DockerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerDemoApplication.class, args);
    }
}

@RestController
class GreetingController {
    // Reads the environment variable 'GREETING'; defaults to "Hello" if not set
    @Value("${GREETING:Hello}")
    private String greeting;

    @GetMapping(    "/greet")
    public String greet() {
        return greeting + ", Docker!";
    }
}