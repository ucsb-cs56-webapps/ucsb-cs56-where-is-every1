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

	@Autowired
	private CustomerRepository repository;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String customerForm(Model model) {
		model.addAttribute("roomrequest", new RoomRequest()); 
		ArrayList<String> suggestions = new ArrayList<String>();
		for (Building b : repository.findAll()) {
			suggestions.add(b.getBuildingName());
		}
		
		model.addAttribute("buildinglist", suggestions);
		return "form";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String customerSubmit(@ModelAttribute RoomRequest roomRequest, Model model) {

		//model.addAttribute("roomrequest", roomRequest);
		Building FoundBuilding = repository.findByBuildingName(roomRequest.getBuildingName());
		if (FoundBuilding == null) {
			FoundBuilding = new Building("No such building found", 666);
		}
		
		model.addAttribute("customer", FoundBuilding);
		
		//repository.findByBuildingName(roomRequest.getBuildingName());
		String info = String.format("Customer Submission: Building = %s, Room Number = %d", roomRequest.getBuildingName(),
				roomRequest.getRoomNumber());
		
		//System.out.println(repository.findByBuildingName(roomRequest.getBuildingName()));
		
		log.info(info);

		return "result";
	}
}
