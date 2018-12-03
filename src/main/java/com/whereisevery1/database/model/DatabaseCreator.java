package com.whereisevery1.database.model;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DatabaseCreator {

	private SerializableBuildingList data;

	public void runScrape() {
		data = new SerializableBuildingList(Scraper.scrapeUCSB());
	}

	public void writeToJSON() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("catalog.json"), data);

			// Convert object to JSON string
			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			//System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			//jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			//System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setData(SerializableBuildingList data) {
		this.data = data;
	}
	
}
