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
	private FacilityDetail detailsAboutFacility;
	
	//public Facility() {}
	
	/*public Facility(String name, int facilityID, int numberOfRooms) {
		facilityID = this.facilityID;
		addFacilityDetail(1, name);
		addFacilityDetail(2, facilityID);
		addFacilityDetail(3, numberOfRooms);
		
		for(int i = 1; i <= numberOfRooms; i++) {
			createRoom(i);
		}
	}*/
	
	
	public FacilityDetail getDetailsAboutFacility() {
		return detailsAboutFacility;
	}

	public void setDetailsAboutFacility(FacilityDetail detailsAboutFacility) {
		this.detailsAboutFacility = detailsAboutFacility;
	}

	public void setFacilityID(int facilityID) {
		this.facilityID = facilityID;
	}

	public int getFacilityID() {
		return facilityID;
	}

	


	
}
