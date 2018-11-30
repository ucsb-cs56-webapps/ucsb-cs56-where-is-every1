package com.whereisevery1.database.model;

import com.whereisevery1.database.model.Room;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.data.annotation.Id;

public class Building {
	
	@Id
	public String id;
	
	private HashMap<Integer,Room> rooms;
	private String name;
	
	public Building() {}
	
	public Building(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public HashMap<Integer,Room> getRooms(){
		return rooms;
	}
	
	public ArrayList<Integer> getRoomNumbers(){
		return new ArrayList<Integer>(rooms.keySet());
	}
	
	public Room getBySpecificNumber(int number) {
		return rooms.get(number);
	}
	
	public void addToRoom(int roomNumber, String dates, String times) {
		if(!this.rooms.containsKey(roomNumber))
			rooms.put(roomNumber, new Room(roomNumber));
		
		rooms.get(roomNumber).addTimesDates(dates, times);
	}
}


