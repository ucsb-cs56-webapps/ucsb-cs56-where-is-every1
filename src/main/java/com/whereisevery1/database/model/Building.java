package com.whereisevery1.database.model;

import com.whereisevery1.database.model.Room;
import java.util.ArrayList;
import java.util.HashMap;

//import lombok.Data;

/** Represents a Building
*/
public class Building {
	
	/** Holds rooms in HashMap with int room number as the key
	*/
	private HashMap<String, Room> rooms;
	private String name;

	public Building() {
	}
	
	/** Creates Building with specified name
	*@param name Building’s name
	*/
	public Building(String name) {
		this.name = name;
		rooms = new HashMap<String, Room>();
	}

	/** Gets Building’s name
	* @return String representing Building’s name
	*/
	public String getName() {
		return this.name;
	}

	/** Gets rooms in the Building
	* @return a HashMap containing rooms in the Building
	*/
	public HashMap<String, Room> getRooms() {
		return rooms;
	}

	/** Gets the room numbers of the Building.
	* HashMap keyset() creates a set out of the key elements contained in the hash map
	*@return a ArrayList of Integers containing room numbers of all the rooms in the Building
	*/
	public ArrayList<String> getRoomNumbers() {
		return new ArrayList<String>(rooms.keySet());
	}

	/** Sets rooms 
	*@param rooms HashMap containing Rooms
	*/
	public void setRooms(HashMap<String, Room> rooms) {
		this.rooms = rooms;
	}
	
	/**Sets name of building
	*@param name name
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/** Gets a Room depending on its room number
	* @param number key value of the Room
	* @return a Room object specified by key value
	*/
	public Room getBySpecificNumber(String number) {
		return rooms.get(number);
	}
	
	/** Adds dates and times to a Room.
	* If the Room at the given room number does not exist in rooms, 
	* it is  created and added to rooms.
	* @param roomNumber room number
	* @param dates date
	* @param times times
	*/
	public void addToRoom(String roomNumber, String dates, String times) {
		if (!this.rooms.containsKey(roomNumber))
			rooms.put(roomNumber, new Room(roomNumber));

		rooms.get(roomNumber).addTimesDates(dates, times);
	}
}
