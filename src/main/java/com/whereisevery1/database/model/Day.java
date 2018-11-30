package com.whereisevery1.database.model;

import java.util.ArrayList;

import com.whereisevery1.database.model.Time; 

public class Day{
	private String day;
	private ArrayList<Time> times;
	
	public Day(String d) {
		this.day = d;
	}
	
	public String getDay() {return this.day;}
	public ArrayList<Time> getTimes(){return this.times;}

	public void addTimes(String t) {
		try {times.add(new Time(t));}
		catch(Exception e) {}
	}
}

