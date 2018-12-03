package com.whereisevery1.database.model;

import java.util.ArrayList;

import com.whereisevery1.database.model.Time;
import lombok.Data;

public class Day {
	private String day;
	private ArrayList<Time> times;

	public Day() {

	}

	public Day(String d) {
		this.day = d;
		times = new ArrayList<Time>();
	}

	public String getDay() {
		return this.day;
	}

	public ArrayList<Time> getTimes() {
		return this.times;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setTimes(ArrayList<Time> times) {
		this.times = times;
	}

	public void addTimes(String t) {
		try {
			times.add(new Time(t));
		} catch (Exception e) {
		}
	}
}
