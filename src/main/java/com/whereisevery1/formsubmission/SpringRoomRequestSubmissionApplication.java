package com.whereisevery1.formsubmission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.whereisevery1.database.model.Scraping;

@SpringBootApplication
public class SpringRoomRequestSubmissionApplication {

	public static void main(String[] args) {
		
		Scraping scraper = new Scraping();
		
		//SpringApplication.run(SpringRoomRequestSubmissionApplication.class, args);
	}
}
