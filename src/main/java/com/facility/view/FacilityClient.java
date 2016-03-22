package com.facility.view;


import java.util.List;

import com.facility.base.*;
import com.facility.service.*;

public class FacilityClient {

	public FacilityClient() throws Exception {
		FacilityService facService = new FacilityService();
		
		System.out.println("\nFacilityClient: *************** Instantiating a facility and its details *************************");
        Facility fact = new Facility();;
		fact.setFacilityID(1);
		FacilityDetail detail = new FacilityDetail();
		detail.setFacilityID(1);
		detail.setName("Library");
		detail.setNumberOfRooms(2);
		//detail.setPhoneNumber(5550123);
        fact.setDetailsAboutFacility(detail);
      
        //save facility information
        //Saving the newly created facility and its details
        facService.addNewFacility(fact);
        System.out.println("FacilityClient: *************** Facility is inserted in Facility Database *************************");
		
        System.out.println("FacilityClient: *************** Trying to search facility in the database *************************");
        
 
        Facility searchedFacility = facService.getFacilityInformation(1); 
        
        System.out.println("\nFacilityInformation: *************** Here is searched facility information *************************");
        System.out.println("\n\tFacility ID:   \t\t" + searchedFacility.getFacilityID());
        FacilityDetail facilityDet = searchedFacility.getDetailsAboutFacility();
        System.out.println("\tInfo About Facility:  \t" + facilityDet.getName() + 
          		"\n\t\t\t\t Number of Rooms:" + facilityDet.getNumberOfRooms()); 
        if (facilityDet.getPhoneNumber() != 0) {
        	System.out.print("\t\t\t\t Phone Number: " + facilityDet.getPhoneNumber() +
        	"\n\t\t\t\t" + "\n");
        } else {
        	System.out.print("\t\t\t\t Phone Number: unlisted" +
                	"\n\t\t\t\t" + "\n");
        }
        		
        
        //add optional phone number to facility detail
		facService.addFacilityDetail(1, 3120136);
		
		Facility updatedFacility = facService.getFacilityInformation(1); 
		FacilityDetail facilityNewDet = updatedFacility.getDetailsAboutFacility();
		
		System.out.println("\nFacilityInformation: *************** Here is the updated facility information *************************");
        System.out.println("\n\tFacility ID:   \t\t" + updatedFacility.getFacilityID());
        System.out.println("\tInfo About Facility:  \t" + facilityNewDet.getName() + 
          		"\n\t\t\t\t Number of Rooms: " + facilityNewDet.getNumberOfRooms()); 
        if (facilityNewDet.getPhoneNumber() != 0) {
        	System.out.print("\t\t\t\t Phone Number: " + facilityNewDet.getPhoneNumber() +
        	"\n\t\t\t\t" + "\n");
        } else {
        	System.out.print("\t\t\t\t Phone Number: unlisted" +
                	"\n\t\t\t\t" + "\n");
        }
		
        
        System.out.println("\nFacilityInformation: *************** Here is the list of facilities *************************");
        List<Facility> listOfFacilities = facService.listFacilities();
        for (Facility fac : listOfFacilities) {
        	FacilityDetail facDet = fac.getDetailsAboutFacility();
        	System.out.println("\n\t" + facDet.getName() + " ID: " + fac.getFacilityID());
        }
        
          
         /*System.out.println("Remove Facility"); 
         facService.removeFacility(3);
         System.out.println("Facility Removed");*/
	}
}
