package com.whereisevery1.formsubmission.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.whereisevery1.formsubmission.model.RoomRequest;
import com.whereisevery1.database.model.*;

@Controller
public class RoomRequestController {

	// @Autowired
	// private CustomerRepository repository;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String customerForm(Model model) {
		if (!model.containsAttribute("roomrequest")) {
			model.addAttribute("roomrequest", new RoomRequest());
			//buildBuildingList(model);

		}
		return "form";
	}

//	public void buildBuildingList(Model model) {
//
//		ArrayList<Building> suggestions = new ArrayList<Building>();
//		for (Building b : repository.findAll()) {
//			suggestions.add(b);
//		}
//		model.addAttribute("buildinglist", suggestions);
//
//	}

	/*
	 * public void buildRoomList(Model model, Building b) {
	 * 
	 * ArrayList<Room> roomList = new ArrayList<Room>(); // System.out.println(b);
	 * for (Room r : b.getRooms()) { // System.out.println(r); roomList.add(r); }
	 * model.addAttribute("roomlist", roomList); }
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String customerSubmit(@ModelAttribute RoomRequest roomRequest, Model model) {

		// model.addAttribute("roomrequest", roomRequest);
		model.addAttribute("building", new Building("Building"));
		// Building foundBuilding =
		// repository.findByBuildingName(roomRequest.getBuildingName());
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
		return "result";
	}
}
