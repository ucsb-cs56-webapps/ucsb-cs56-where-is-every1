package com.whereisevery1.database.model;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
public class SerializableBuildingList {

	private HashMap<String, Building> buildings;

	public SerializableBuildingList() {
	}

	public SerializableBuildingList(HashMap<String, Building> buildings) {
		this.buildings = buildings;
	}

	public HashMap<String, Building> getBuildings() {
		return this.buildings;
	}

	public void setBuildings(HashMap<String, Building> buildings) {
		this.buildings = buildings;
	}
}
