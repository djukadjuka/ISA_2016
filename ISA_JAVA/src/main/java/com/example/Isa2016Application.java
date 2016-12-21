package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Isa2016Application{

	public static void main(String[] args) {
		SpringApplication.run(Isa2016Application.class, args);
	}
	
}
