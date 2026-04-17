package com.marcelo.vogel.experiment_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ExperimentTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExperimentTrackerApplication.class, args);
	}

}
