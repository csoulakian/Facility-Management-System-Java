/**
 * 
 */
package com.facility.base;

import java.util.List;


/**
 * @author Chrissy
 *
 */
public class Facility {
	
	private int facilityID;
	List<FacilityDetail> listOfDetails;
	List<Room> listOfRooms;
	
	public Facility() {}
	
	public Facility(String name, int facilityID, int numberOfRooms) {
		facilityID = this.facilityID;
		addFacilityDetail(1, name);
		addFacilityDetail(2, facilityID);
		addFacilityDetail(3, numberOfRooms);
		
		for(int i = 1; i <= numberOfRooms; i++) {
			createRoom(i);
		}
	}
	

	public void addFacilityDetail(int type, Object detailAboutFacility) {
		FacilityDetail detail = new FacilityDetail(type, detailAboutFacility);
		listOfDetails.add(detail);
	}
	
	public int getFacilityID() {
		return facilityID;
	}
	
	public void createRoom(int roomNumber) {
		Room room = new Room(roomNumber);
		listOfRooms.add(room);
	}
	
	public List<FacilityDetail> getFacilityInformation() {
		return listOfDetails;
	}

	
}
