package com.whereisevery1.formsubmission.model;

/** Represents a User’s request
*/
public class RoomRequest {

	private String roomNumber;
	private String buildingName;
	private String day;
	
	/** Converts user’s request to uppercase
	*/
	public void toUpper() {
		this.roomNumber = roomNumber.toUpperCase();
		this.buildingName = buildingName.toUpperCase();
		this.day = day.toUpperCase();
	}

	/**Gets buildingName
	*@return name of the building
	*/
	public String getBuildingName() {
		return buildingName;
	}

	/** Sets buildingName
	*@param buildingName the name of the building the user wants
	*/
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	/**Gets room number
	*@return room number
	*/
	public String getRoomNumber() {
		return roomNumber;
	}

	/** sets roomNumber
	*@param roomNumber a room number
	*/
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	/** sets day
	*@param day day user wants to check
	*/
	public void setDay(String day) {
		this.day = day;
	}

	/** Gets day
	*@return day user wants to check
	*/
	public String getDay() {
		return this.day;
	}
}
