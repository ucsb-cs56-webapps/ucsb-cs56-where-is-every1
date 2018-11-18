package com.whereisevery1.formsubmission.controller;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.whereisevery1.database.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByBuildingName(String buildingName);
    public List<Customer> findByRoomNumber(long roomNumber);

}
