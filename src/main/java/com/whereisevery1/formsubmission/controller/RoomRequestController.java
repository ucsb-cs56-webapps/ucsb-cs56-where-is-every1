package com.whereisevery1.formsubmission.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	// @Autowired
	// private CustomerRepository repository;

	Logger log = LoggerFactory.getLogger(this.getClass());
	SerializableBuildingList buildings;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String customerForm(Model model) {
		if (!model.containsAttribute("roomrequest")) {
			model.addAttribute("roomrequest", new RoomRequest());
			buildBuildingList(model);
		}
		return "form";
	}

	public void buildBuildingList(Model model) {
		
		if (buildings == null) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				buildings = mapper.readValue(new File("catalog.json"), SerializableBuildingList.class);
				model.addAttribute("buildinglist", buildings.getBuildings().keySet());
				//System.out.println(buildings.getBuildings().keySet());
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			model.addAttribute("buildinglist", buildings.getBuildings().keySet());
		}

//		ArrayList<Building> suggestions = new ArrayList<Building>();
//		for (Building b : repository.findAll()) {
//			suggestions.add(b);
//		}
//		model.addAttribute("buildinglist", suggestions);

	}

	public void buildRoomList(Model model, String building) {

		// ArrayList<Room> roomList = new ArrayList<Room>(); // System.out.println(b);

//		for (Room r : b.getRooms()) {
//			System.out.println(r);
//			roomList.add(r);
//		}
		model.addAttribute("roomlist", buildings.getBuildings().get(building).getRoomNumbers());
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String customerSubmit(@ModelAttribute RoomRequest roomRequest, Model model) {

		// model.addAttribute("roomrequest", roomRequest);

		model.addAttribute("", new Building("Building"));
		// Building foundBuilding =
		// repository.findByBuildingName(roomRequest.getBuildingName());

		if (buildings != null) {
			if (roomRequest.getRoomNumber() == "") {
				RoomRequest currentBuildingRequest = new RoomRequest();
				currentBuildingRequest.setBuildingName(roomRequest.getBuildingName());
				model.addAttribute("roomrequest", currentBuildingRequest);
				model.addAttribute("buildinglist", buildings.getBuildings().keySet());
				// buildBuildingList(model);
				buildRoomList(model, roomRequest.getBuildingName());
				return "form";
			} else {
				model.addAttribute("roomrequest", roomRequest);
				HashMap<String, Day> dayMap = buildings.getBuildings().get(roomRequest.getBuildingName()).getRooms()
						.get(Integer.parseInt(roomRequest.getRoomNumber())).getTimes();

//				String day = LocalDateTime.now(ZoneId.of("America/Los_Angeles")).getDayOfWeek().toString();
//				switch (day) {
//				case "SUNDAY":
//					day = "SU";
//					break;
//				case "MONDAY":
//					day = "M";
//					break;
//				case "TUESDAY":
//					day = "T";
//					break;
//				case "WEDNESDAY":
//					day = "W";
//					break;
//				case "THURSDAY":
//					day = "R";
//					break;
//				case "FRIDAY":
//					day = "F";
//					break;
//				case "SATURDAY":
//					day = "S";
//					break;
//				}
				String day = "W";

				if (dayMap.get(day) == null) {
					return "freeRoomResult";
				} else {
					//System.out.println("TIMES ARE" + dayMap.get(day).getTimes());

					model.addAttribute("day", dayMap.get(day));
					return "result";

				}

			}
		}

		/*
		 * if (foundBuilding == null) { foundBuilding = new
		 * Building("No such building found", 666); }
		 * 
		 * if (roomRequest.getRoomNumber() == "") { RoomRequest currentBuildingRequest =
		 * new RoomRequest();
		 * currentBuildingRequest.setBuildingName(roomRequest.getBuildingName());
		 * model.addAttribute("roomrequest", currentBuildingRequest);
		 * buildBuildingList(model); buildRoomList(model, foundBuilding);
		 * 
		 * return "form"; } else { model.addAttribute("building", foundBuilding);
		 * 
		 * // repository.findByBuildingName(roomRequest.getBuildingName()); String info
		 * = String.format("Customer Submission: Building = %s, Room Number = %s",
		 * roomRequest.getBuildingName(), roomRequest.getRoomNumber());
		 * 
		 * //
		 * System.out.println(repository.findByBuildingName(roomRequest.getBuildingName(
		 * )));
		 * 
		 * log.info(info);
		 * 
		 * return "result"; }
		 */
		return "form";
	}
}
