package com.example.vritual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.vritual.entities")
public class VritualApplication {
	public static void main(String[] args) {
		SpringApplication.run(VritualApplication.class, args);
	}
}
