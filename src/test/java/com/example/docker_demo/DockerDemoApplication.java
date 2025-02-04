package com.example.docker_demo;

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

