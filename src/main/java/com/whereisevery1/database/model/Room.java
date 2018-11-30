package com.whereisevery1.database.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import com.whereisevery1.database.model;

public class Room {

    @Id
    public long id;

    private HashMap<String,Day> times_day;
    private int number;

    public Room(int number) {
    	this.times_day = new HashMap<String,Day>();
        this.number = number;
    }

    public int getNumber() {
    	return number;
    }

    public HashMap<String,Day> getTimes(){
    	return times_day;
    }
    public void addTimesDates(String days, String times) {
    	String [] d = day.split("\\s");

    	for(String day : d) {
    		if(!this.times_day.containsKey(day))
			this.times_day.put(day, new Day(day));
		this.times_day.get(day).addTimes(times);
    }
}
