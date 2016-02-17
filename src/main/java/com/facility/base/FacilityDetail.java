package com.facility.base;

public class FacilityDetail {

	private int type;
	private String name;
	private int facilityID;
	private int numberOfRooms;
	private int actualUsage;
	private double usageRate;
	
	
	FacilityDetail(int type, Object detail) {
		this.type = type;
		
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
		}
		
	}
	
	private void setName(String nameOfFacility) {
		name = nameOfFacility;
	}
	
	private void setFacilityID(int idOfFacility) {
		facilityID = idOfFacility;
	}
	
	private void setNumberOfRooms(int totalRooms) {
		numberOfRooms = totalRooms;
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
	
	//TODO 
	public int getActualUsage() {
		return 0;
	}
	
	//TODO
	public double getUsageRate() {
		return 0.0;
	}
	
	
}
