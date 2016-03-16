package com.facility.view;


import com.facility.base.*;
import com.facility.service.*;

public class FacilityClient {

	public static void main (String args[]) throws Exception {
		FacilityService facService = new FacilityService();
		
		/*System.out.println("FacilityClient: *************** instantiating a facility and its details *************************");
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
		
        System.out.println("FacilityClient: *************** trying to search facility in the database *************************");
        
 
        Facility searchedFacility = facService.findFacilityById(1); 
        
        System.out.println("FacilityInformation: *************** Here is searched facility information *************************");
          System.out.println("\tName: \t\t\t" + searchedFacility.getFacilityID() + "\n");
          FacilityDetail facilityDet = searchedFacility.getDetailsAboutFacility();
          System.out.println("\tInformation About Facility:\t" + facilityDet.getName() + 
          		"\n\t\t\t\t Phone Number: " + facilityDet.getPhoneNumber() +
          		"\n\t\t\t\t Number of Rooms:" + facilityDet.getNumberOfRooms() + 
          		"\n\t\t\t\t" + "\n");
		*/
          
         System.out.println("Remove Facility"); 
         facService.removeFacility(3);
         System.out.println("Facility Removed");
	}
}