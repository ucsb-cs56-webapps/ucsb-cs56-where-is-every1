package com.whereisevery1.database.model;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import lombok.Data;


@JsonSerialize
/** Represents a Serializable object containing all building data including 
* Buildings, Room, Days, Times 
*/
public class SerializableBuildingList {

	private HashMap<String, Building> buildings;

	public SerializableBuildingList() {
	}
	
	/** Creates SerializableBuildingList object with a specified HashMap containing Buildings
	*@param buildings HashMap containing Buildings with key Strings
	*/
	public SerializableBuildingList(HashMap<String, Building> buildings) {
		this.buildings = buildings;
	}
	
	/**Gets buildings
	*@return HashMap of Buildings
	*/
	public HashMap<String, Building> getBuildings() {
		return this.buildings;
	}
	
	/**Sets buildings 
	*@param buildings a HashMap of Buildings
	*/
	public void setBuildings(HashMap<String, Building> buildings) {
		this.buildings = buildings;
	}
}
