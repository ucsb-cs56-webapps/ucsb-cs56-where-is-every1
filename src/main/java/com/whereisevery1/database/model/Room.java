package com.whereisevery1.database.model;

import org.springframework.data.annotation.Id;

public class Room {

    @Id
    public long id;
	
    public String freeTimes;
    public long roomNumber;

    public Room() {}

    public Room(long roomNumber, String freeTimes) {
        this.freeTimes = freeTimes;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return String.format(
                "Room[id=%s, freeTimes='%s', roomNumber='%s']",
                id, freeTimes, roomNumber);
    }
    
    
}
