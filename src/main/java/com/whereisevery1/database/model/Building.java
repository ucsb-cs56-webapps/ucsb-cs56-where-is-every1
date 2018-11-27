package com.whereisevery1.database.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class Building {

    @Id
    public String id;

    public String buildingName;
    public long roomNumber;
    public ArrayList<Room> rooms = new ArrayList<Room>();

    public Building() {}

    public Building(String buildingName, long roomNumber) {
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
        //this.rooms = rooms;
    }

    public String getBuildingName() {
    	return buildingName;
    }
    
    public ArrayList<Room> getRooms() {
    	return this.rooms;
    }
    
    @Override
    public String toString() {
        
    	return rooms.toString();
//    	return String.format(
//                "Building[id=%s, buildingName='%s', rooms='%s']",
//                id, buildingName, rooms);
    }

}

