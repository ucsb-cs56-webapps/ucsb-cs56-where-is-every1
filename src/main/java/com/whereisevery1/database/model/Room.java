package com.whereisevery1.database.model;

import java.util.HashMap;

//import org.springframework.data.annotation.Id;
import com.whereisevery1.database.model.Day;
//import lombok.Data;

/** Represents a Room
*/
public class Room {

	// @Id
	// public long id;
	
	// public long id;
	/** Holds Day objects in HashMap with a String as key
	*/
	private HashMap<String, Day> times;
	private String number;

	public Room() {
	}

	/** Creates Room with specified room number
	* @param number Room’s number
	*/
	public Room(String number) {
		this.number = number;
		this.times = new HashMap<String, Day>();
	}
	
	/** Sets Room’s number
	* @param number Room number
	*/
	public void setNumber(String number) {
		this.number = number;
	}
	
	/** Sets Room’s HashMap of times 
	* @param times HashMap of Days containing times that classes are held 
	*/
	public void setTimes(HashMap<String, Day> times) {
		this.times = times;
	}

	/** Gets room number
	*@return a room number
	*/
	public String getNumber() {
		return number;
	}

	/** Gets times
	*@return times 
	*/
	public HashMap<String, Day> getTimes() {
		return times;
	}
	
	/** Adds Days to times and adds Times to the Days
	* If the Day does not exist in times, 
	* it is  created and added to times.
	* @param days a String of all the days a class meets, to be split by whitespace
	* @param times the times that the class meets 
	*/
	public void addTimesDates(String days, String times) {
		String[] d = days.split("\\s");

		for (String day : d) {
			if (!this.times.containsKey(day))
				this.times.put(day, new Day(day));
			this.times.get(day).addTimes(times);
		}
	}
}
