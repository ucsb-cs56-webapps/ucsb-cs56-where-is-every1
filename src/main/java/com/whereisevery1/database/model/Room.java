package com.whereisevery1.database.model;

import java.util.HashMap;

//import org.springframework.data.annotation.Id;
import com.whereisevery1.database.model.Day;
//import lombok.Data;

public class Room {

	// @Id
	// public long id;

	private HashMap<String, Day> times;
	private int number;

	public Room() {
	}

	public Room(int number) {
		this.number = number;
		this.times = new HashMap<String, Day>();
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setTimes(HashMap<String, Day> times) {
		this.times = times;
	}

	public int getNumber() {
		return number;
	}

	public HashMap<String, Day> getTimes() {
		return times;
	}

	public void addTimesDates(String days, String times) {
		String[] d = days.split("\\s");

		for (String day : d) {
			if (!this.times.containsKey(day))
				this.times.put(day, new Day(day));
			this.times.get(day).addTimes(times);
		}
	}
}
