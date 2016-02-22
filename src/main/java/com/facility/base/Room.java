package com.facility.base;

public class Room extends Facility {
	
	private int roomNumber;
	private int facilityID;
	
	public Room (int roomNumber) {
		facilityID = super.getFacilityID();
		this.roomNumber = roomNumber;
	}
	
	public int getFacilityID() {
		return facilityID;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
}
