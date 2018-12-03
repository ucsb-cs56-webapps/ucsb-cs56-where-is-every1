package com.whereisevery1.formsubmission.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.whereisevery1.formsubmission.model.RoomRequest;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whereisevery1.database.model.*;

@Controller
public class RoomRequestController {

	// This is left in for future implementation of mongoDB
	// @Autowired
	// private CustomerRepository repository;

	// This is where the json file ends up on the heroku server. Locally it should
	// live in resources.
	public static final String jsonLocation = "target/classes/catalog.json";

	// Logger log = LoggerFactory.getLogger(this.getClass());
	SerializableBuildingList buildings;

	// This request mapping provides the home page.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String customerForm(Model model) {
		// Check if the model already has a room request object. This allows us to
		// rebuild the page
		// with suggestions for room numbers after the user selects a building. Right
		// now, when the building input
		// loses focus, the page submits, and returns to the form, but uses thymeleaf to
		// now generate a list of rooms
		// for the user to choose from.
		if (!model.containsAttribute("roomrequest")) {
			model.addAttribute("roomrequest", new RoomRequest());
			buildBuildingList(model);
		}
		return "form";
	}

	// This function checks to see if the json database has been read. If it has, it
	// uses the data stored in buildings already.
	// If it hasn't, it gets them from the json database.
	public void buildBuildingList(Model model) {
		if (buildings == null) {
			ObjectMapper mapper = new ObjectMapper();

			try {
				buildings = mapper.readValue(new File(jsonLocation), SerializableBuildingList.class);
				model.addAttribute("buildinglist", buildings.getBuildings().keySet());
				// System.out.println(buildings.getBuildings().keySet());
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// The list of buildings is all the keys in the building hashmap.
			model.addAttribute("buildinglist", buildings.getBuildings().keySet());
		}
	}

	// Build the list of rooms, by using the string building to get the proper
	// building, and then getting the array of rooms.
	// Thymeleaf then uses this list in the 'form' template to build the suggestion
	// list.
	public void buildRoomList(Model model, String currentBuilding) {

		ArrayList<Integer> roomList;

		if (buildings.getBuildings().get(currentBuilding) == null) {
			roomList = new ArrayList<Integer>();
		} else {
			roomList = buildings.getBuildings().get(currentBuilding).getRoomNumbers();
		}
		model.addAttribute("roomlist", roomList);
	}

	/*
	 * TODO: Rewrite the page to not have to reload on building list losing focus.
	 * It's annoying, and probably should be done in javascript. Also, move most of
	 * this logic into its own set of classes.
	 */
	// If a room number was provided, then display the results of the database
	// query. If only a building was provided,
	// return to form, but rebuilding the page with a list of room numbers.
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String customerSubmit(@ModelAttribute RoomRequest roomRequest, Model model) {

		// The room request is needed for both a return to form and a move to result*.
		model.addAttribute("roomrequest", roomRequest);

		// If we were provided a building, then build the roomList and return the form.
		if (buildings != null) {
			if (roomRequest.getRoomNumber() == "") {
				// Re-add the roomRequest to the model, so that it can be used to fill in the
				// form on reload.
				buildBuildingList(model);
				buildRoomList(model, roomRequest.getBuildingName());
				return "form";
			} else {

				// model.addAttribute("roomrequest", roomRequest);

				// Check the input for validity.
				if (buildings.getBuildings() == null
						|| buildings.getBuildings().get(roomRequest.getBuildingName()) == null
						|| buildings.getBuildings().get(roomRequest.getBuildingName()).getRooms() == null
						|| buildings.getBuildings().get(roomRequest.getBuildingName()).getRooms()
								.get(Integer.parseInt(roomRequest.getRoomNumber())) == null) {
					return "badInput";
				} else {
					// The user has entered the form with a building and a room, so now we have to
					// find all the mappings of the room based on day and time.

					try {
						Integer.parseInt(roomRequest.getRoomNumber());
					} catch (NumberFormatException e) {
						return "badInput";
					}

					HashMap<String, Day> dayMap = buildings.getBuildings().get(roomRequest.getBuildingName()).getRooms()
							.get(Integer.parseInt(roomRequest.getRoomNumber())).getTimes();

					// This code find the day that it is today using PST.
					String day = LocalDateTime.now(ZoneId.of("America/Los_Angeles")).getDayOfWeek().toString();

					if (!roomRequest.getDay().equals("Today")) {
						day = roomRequest.getDay();
					} else {
						switch (day) {
						case "SUNDAY":
							day = "SU";
							break;
						case "MONDAY":
							day = "M";
							break;
						case "TUESDAY":
							day = "T";
							break;
						case "WEDNESDAY":
							day = "W";
							break;
						case "THURSDAY":
							day = "R";
							break;
						case "FRIDAY":
							day = "F";
							break;
						case "SATURDAY":
							day = "S";
							break;
						default:
							// day = roomRequest.getDay();
							break;
						}
					}
					// If there are not classes at all the the room today, display a special
					// template that tells the user the class is free all day. If there are classes,
					// display the class schedule.
					if (dayMap.get(day) == null) {
						return "resultFree";
					} else {
						model.addAttribute("day", dayMap.get(day));
						return "result";
					}
				}
			}
		}
		// If there was no building, just go back to the input form.
		return "form";
	}
}
