package com.whereisevery1.database.model;

import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    public String id;

    public String buildingName;
    public long roomNumber;

    public Customer() {}

    public Customer(String buildingName, long roomNumber) {
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, buildingName='%s', lastName='%s']",
                id, buildingName, roomNumber);
    }

}

