package com.rsn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@SpringBootApplication
@EnableJpaRepositories
public class RsnApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsnApplication.class, args);
	}

}
