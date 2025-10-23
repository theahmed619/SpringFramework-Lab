package com.example.yeshendrayt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GmailRecieverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmailRecieverApplication.class, args);
	}

}
