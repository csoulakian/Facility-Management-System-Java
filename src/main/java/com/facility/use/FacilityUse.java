package com.facility.use;

import java.util.Date;

import com.facility.base.Facility;

public class FacilityUse {
	
	boolean inUse;

	public FacilityUse() {}
	
	public void assignFacilityToUse(Facility facility, Client client, Date start, Date end) {
		
	}
	
	public boolean isInUseDuringInterval(Facility facility, Date start, Date end) {
		return true;
	}
	
}
