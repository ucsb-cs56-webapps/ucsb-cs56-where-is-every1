package com.whereisevery1.formsubmission;

//import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//import com.whereisevery1.database.model.Building;
import com.whereisevery1.database.model.DatabaseCreator;
//import com.whereisevery1.database.model.Scraping;
//import com.whereisevery1.database.model.SerializableBuildingList;


@SpringBootApplication
public class SpringRoomRequestSubmissionApplication {

	public static void main(String[] args) {
		
		// If you provide an argument when running the code, it will build the json database.
		if (args.length > 0) {
			DatabaseCreator d = new DatabaseCreator();
			d.runScrape();
			d.writeToJSON();
		}
		else {
			// Start the spring application if we're not building the database.
			SpringApplication.run(SpringRoomRequestSubmissionApplication.class, args);			
		}
	}
}
