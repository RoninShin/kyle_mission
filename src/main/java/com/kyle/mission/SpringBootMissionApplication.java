package com.kyle.mission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class SpringBootMissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMissionApplication.class, args);
	}
}
