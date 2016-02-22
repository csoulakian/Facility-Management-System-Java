package com.facility.manager;

import java.util.List;

import com.facility.base.Facility;

public class FacilityManager {
	
	private List<Facility> listOfFacilities;
	
	public FacilityManager() {}
	
	public Facility addNewFacility(String name, int facilityID, int numberOfRooms) {
		Facility facility = new Facility(name, facilityID, numberOfRooms);
		if(!facilityExists(facilityID)) {
			listOfFacilities.add(facility);
			return facility;
		} else {
			throw new IllegalArgumentException("This facility already exists.");
		}
	}
	
	public String removeFacility(int facilityID) {
		if(facilityExists(facilityID)) {
			Facility facility = getFacility(facilityID);
			listOfFacilities.remove(facility);
		} else {
			return "This facility does not exist.";
		}
		if(!facilityExists(facilityID)) {
			return "Facility successfully removed.";
		} else {
			return "Error removing facility.";
		}
	}
	
	// checks if a facility with a particular facilityID already exists
	public boolean facilityExists(int facilityID) {
		boolean exist = false;
		if(getFacility(facilityID) != null) {
			exist = true;
		}
		return exist;
	}
	
	public Facility getFacility(int facilityID) {
		Facility facility = null;
		for(Facility f : listOfFacilities) {
			if(f.getFacilityID() == facilityID) {
				facility = f;
			}
		}
		return facility;
	}
	
	public List<Facility> listFacilities() {
		return listOfFacilities;
	}

}
