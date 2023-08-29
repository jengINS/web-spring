package com.tedinno.ace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tedinno.ace")
public class AceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AceApplication.class, args);
	}

}
