package com.whereisevery1.formsubmission.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whereisevery1.formsubmission.model.RoomRequest;

@Controller
public class RoomRequestController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String customerForm(Model model) {
		model.addAttribute("roomrequest", new RoomRequest());
		return "form";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String customerSubmit(@ModelAttribute RoomRequest roomRequest, Model model) {

		model.addAttribute("roomrequest", roomRequest);
		String info = String.format("Customer Submission: Building = %s, Room Number = %d", roomRequest.getBuildingName(),
				roomRequest.getRoomNumber());
		log.info(info);

		return "result";
	}
}
