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
		
		//SpringApplication.run(SpringRoomRequestSubmissionApplication.class, args);


		// DatabaseCreator d = new DatabaseCreator();
		// d.runScrape();
		// d.writeToJSON();

		// HashMap<String, Building> buildings = new HashMap<String, Building>();
		// Building b = new Building("HFH");
		// buildings.put("HFH", b);
		// SerializableBuildingList l = new SerializableBuildingList(buildings);
		// d.setData(l);
		// d.runScrape();
		// Scraping scraper = new Scraping();

		SpringApplication.run(SpringRoomRequestSubmissionApplication.class, args);

	}
}
