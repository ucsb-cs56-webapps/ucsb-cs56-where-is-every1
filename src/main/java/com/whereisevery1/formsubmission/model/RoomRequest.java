package com.whereisevery1.formsubmission.model;

public class RoomRequest {

	private String roomNumber;
	private String buildingName;
	private String day;

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getDay() {
		return this.day;
	}
}