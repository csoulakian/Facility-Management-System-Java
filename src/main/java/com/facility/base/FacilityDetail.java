package com.facility.base;

public class FacilityDetail {

	private int type;
	private String name;
	private int facilityID;
	private int numberOfRooms;
	private int phoneNumber;
	
	
	FacilityDetail(int type, Object detail) {
		type = this.type;
		
		switch (type) {
			case 0:
				setName((String) detail);
				break;
			case 1:
				setFacilityID((Integer) detail);
				break;
			case 2:
				setNumberOfRooms((Integer) detail);
				break;
			case 3:
				setPhoneNumber((Integer) detail);
				break;
		}
		
	}
	
	private void setName(String nameOfFacility) {
		this.name = nameOfFacility;
	}
	
	private void setFacilityID(int idOfFacility) {
		this.facilityID = idOfFacility;
	}
	
	private void setNumberOfRooms(int totalRooms) {
		this.numberOfRooms = totalRooms;
	}
	
	public void setPhoneNumber(int phone) {
		this.phoneNumber = phone;
	}
		
	public String getName() {
		return this.name;
	}
	
	public int getFacilityID() {
		return this.facilityID;
	}
	
	public int getNumberOfRooms() {
		return this.numberOfRooms;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}
	
	
}
