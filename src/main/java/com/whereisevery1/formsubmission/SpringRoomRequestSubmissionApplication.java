package com.whereisevery1.formsubmission;

//import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.whereisevery1.database.model.DatabaseCreator;

@SpringBootApplication
public class SpringRoomRequestSubmissionApplication {

	public static void main(String[] args) {

		if (args != null && args.length > 0) {
			// If you provide an argument when running the code, it will build the json
			// database. DOESN'T WORK ON HEROKU.
			if (args[0].equals("json")) {
				DatabaseCreator d = new DatabaseCreator();
				d.runScrape();
				d.writeToJSON();
				// SpringApplication.run(SpringRoomRequestSubmissionApplication.class, args);
			}
		} else {
			// Start the spring application if we're not building the database.
			SpringApplication.run(SpringRoomRequestSubmissionApplication.class, args);
		}
	}
}
