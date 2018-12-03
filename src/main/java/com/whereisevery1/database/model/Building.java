package com.whereisevery1.database.model;

import com.whereisevery1.database.model.Room;
import java.util.ArrayList;
import java.util.HashMap;

//import lombok.Data;

public class Building {
	private HashMap<String, Room> rooms;
	private String name;

	public Building() {
	}

	public Building(String name) {
		this.name = name;
		rooms = new HashMap<String, Room>();
	}

	public String getName() {
		return this.name;
	}

	public HashMap<String, Room> getRooms() {
		return rooms;
	}

	public ArrayList<String> getRoomNumbers() {
		return new ArrayList<String>(rooms.keySet());
	}

	public void setRooms(HashMap<String, Room> rooms) {
		this.rooms = rooms;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getBySpecificNumber(String number) {
		return rooms.get(number);
	}

	public void addToRoom(String roomNumber, String dates, String times) {
		if (!this.rooms.containsKey(roomNumber))
			rooms.put(roomNumber, new Room(roomNumber));

		rooms.get(roomNumber).addTimesDates(dates, times);
	}
}
