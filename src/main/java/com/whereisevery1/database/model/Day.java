package com.whereisevery1.database.model;

import java.util.ArrayList;

import com.whereisevery1.database.model.Time;
//import lombok.Data;
/** Represents a Day
*/
public class Day {
	
	/** Represents the name of the day
	*/
	private String day;
	/** Contains the times a class is held that day
	*/
	private ArrayList<Time> times;

	public Day() {

	}
	
	/** Creates a Day with a specified name
	* and initializes times
	*@param d name of day
	*/
	public Day(String d) {
		this.day = d;
		times = new ArrayList<Time>();
	}
	
	/** Gets day
	*@return name of day
	*/

	public String getDay() {
		return this.day;
	}

	/**Gets times
	*@return list of times
	*/
	public ArrayList<Time> getTimes() {
		return this.times;
	}
	
	/** Sets day 
	*@param day day name
	*/
	public void setDay(String day) {
		this.day = day;
	}

	/** Sets times
	*@param times list of times
	*/
	public void setTimes(ArrayList<Time> times) {
		this.times = times;
	}
	
	/** Adds a Time to times
	*@param t String representing a time
	*/
	public void addTimes(String t) {
		try {
			times.add(new Time(t));
		} 
		catch (Exception e) {
			//Pass
		}
	}
}
