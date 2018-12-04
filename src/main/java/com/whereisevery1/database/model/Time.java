package com.whereisevery1.database.model;

import java.text.SimpleDateFormat;

/** Represents a time
*/
public class Time {
	// Values entered as military time with format HHmm
	/** Start time, entered as military time with format HHmm
	*/
	private int start;
	
	/** End time, entered as military time with format HHmm
	*/
	private int end;

	// START - IMMUTABLE ATTRIBUTES
	private final static String originalTimeFormat = "hh:mma";
	private final static String militaryFormat = "HHmm";
	// END - IMMUTABLE ATTRIBUTES

	public Time() {
	}
	
	/** Creates new Time by parsing time from string to military time
	*@throws Exception 
	*@param time string to be split by whitespaces and -
	*/
	public Time(String time) throws Exception {
		String[] values = time.split("\\s*-\\s*");

		// Parse the time values from hh:mmxx to HHmm military time
		start = Integer.parseInt((new SimpleDateFormat(militaryFormat))
				.format((new SimpleDateFormat(originalTimeFormat)).parse(values[0])));
		end = Integer.parseInt((new SimpleDateFormat(militaryFormat))
				.format((new SimpleDateFormat(originalTimeFormat)).parse(values[1])));

	}
	
	/** Sets start
	*@param start start time
 	*/
	public void setStart(int start) {
		this.start = start;
	}

	/** Sets end
	*@param end end time
	*/
	public void setEnd(int end) {
		this.end = end;
	}

	/** Gets start
	*@return start time
	*/
	public int getStart() {
		return this.start;
	}

	/** Gets end
	*@return end time
	*/
	public int getEnd() {
		return this.end;
	}
	
	/** Creates message to display the class time
	*@return message “Class starts at:__ Ends at__”
	*/
	public String toString() {
		return "\nClass starts at: " + start + " Ends at " + end;
	}
}
