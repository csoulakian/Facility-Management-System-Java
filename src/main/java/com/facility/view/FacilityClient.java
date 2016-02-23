package com.facility.view;

import com.facility.base.*;
import com.facility.service.*;

public class FacilityClient {

	public static void main (String args[]) throws Exception {
		FacilityService facService = new FacilityService();
		
		System.out.println("FacilityClient: *************** instantiating a facility and its details *************************");
        Facility fact = new Facility();;
		fact.setFacilityID(1);
		FacilityDetail detail = new FacilityDetail();
		detail.setFacilityID(1);
		detail.setName("Library");
		detail.setNumberOfRooms(2);
		detail.setPhoneNumber(5550123);
        fact.setDetailsAboutFacility(detail);
      
        //save facility information
        //Saving the newly created facility and its details
        facService.addNewFacility(fact);
        System.out.println("FacilityClient: *************** Facility is inserted in Facility Database *************************");
		
		
	}
}
