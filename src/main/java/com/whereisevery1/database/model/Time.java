package com.whereisevery1.database.model;

import java.text.SimpleDateFormat;

public class Time {
	// Values entered as military time with format HHmm
	private int start;
	private int end;

	// START - IMMUTABLE ATTRIBUTES
	private static String originalTimeFormat = "hh:mma";
	private static String militaryFormat = "HHmm";
	// END - IMMUTABLE ATTRIBUTES

	public Time() {

	}

	public Time(String time) throws Exception {
		String[] values = time.split("\\s*-\\s*");

		// Parse the time values from hh:mmxx to HHmm military time
		start = Integer.parseInt((new SimpleDateFormat(militaryFormat))
				.format((new SimpleDateFormat(originalTimeFormat)).parse(values[0])));
		end = Integer.parseInt((new SimpleDateFormat(militaryFormat))
				.format((new SimpleDateFormat(originalTimeFormat)).parse(values[1])));

	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}
	
	public String toString() {
		return "\nClass starts at: " + start + " Ends at " + end;
	}
}
