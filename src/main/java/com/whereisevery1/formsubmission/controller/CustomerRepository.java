package com.whereisevery1.formsubmission.controller;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.whereisevery1.database.model.Building;

public interface CustomerRepository extends MongoRepository<Building, String> {

	//public List<Customer> findAllBuildings(String buildingName);
    public Building findByBuildingName(String buildingName);
    public List<Building> findByRoomNumber(long roomNumber);

}
