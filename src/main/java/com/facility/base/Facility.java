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
	
	List<FacilityDetail> listOfDetails;
	
	public Facility(String name, int facilityID, int numberOfRooms) {
		addFacilityDetail(1, name);
		addFacilityDetail(2, facilityID);
		addFacilityDetail(3, numberOfRooms);
		
	}

	public void addFacilityDetail(int type, Object detailAboutFacility) {
		FacilityDetail detail = new FacilityDetail(type, detailAboutFacility);
		listOfDetails.add(detail);
	}
}
