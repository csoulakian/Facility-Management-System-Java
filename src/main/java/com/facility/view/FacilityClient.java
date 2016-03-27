package com.facility.view;


import java.util.List;

import com.facility.base.*;
import com.facility.service.*;

public class FacilityClient {

	public FacilityClient() throws Exception {
		
		FacilityService facService = new FacilityService();
		
		//set up facilities for dummy data
		Facility fact1 = new Facility();
		FacilityDetail factDet1 = new FacilityDetail();
		fact1.setFacilityID(1);
		factDet1.setNumberOfRooms(2);
		fact1.setDetailsAboutFacility(factDet1);
		
		Facility fact3 = new Facility();
		FacilityDetail factDet3 = new FacilityDetail();
		fact3.setFacilityID(3);
		factDet3.setNumberOfRooms(6);
		fact3.setDetailsAboutFacility(factDet3);
		
		Facility fact4 = new Facility();
		FacilityDetail factDet4 = new FacilityDetail();
		fact4.setFacilityID(4);
		factDet4.setNumberOfRooms(5);
		fact4.setDetailsAboutFacility(factDet4);
		
		Facility fact7 = new Facility();
		FacilityDetail factDet7 = new FacilityDetail();
		fact7.setFacilityID(7);
		factDet7.setNumberOfRooms(10);
		fact7.setDetailsAboutFacility(factDet7);
		
		
		System.out.println("\nFacilityClient: *************** Instantiating a facility and its details *************************");
        Facility fact = new Facility();;
		fact.setFacilityID(11);
		FacilityDetail detail = new FacilityDetail();
		detail.setFacilityID(11);
		detail.setName("IT Center");
		detail.setNumberOfRooms(4);
		//detail.setPhoneNumber(5550123);
        fact.setDetailsAboutFacility(detail);
      
        //save facility information
        //Saving the newly created facility and its details
        facService.addNewFacility(fact);
        System.out.println("FacilityClient: *************** Facility is inserted in Facility Database *************************");
		
        System.out.println("FacilityClient: *************** Trying to get information about this facility in the database ***************");
        
        Facility searchedFacility = facService.getFacilityInformation(11); 
        
        System.out.println("\nFacilityClient: *************** Here is searched facility information *************************");
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
		facService.addFacilityDetail(11, 3120136);
		
		Facility updatedFacility = facService.getFacilityInformation(11); 
		FacilityDetail facilityNewDet = updatedFacility.getDetailsAboutFacility();
		
		System.out.println("\nFacilityClient: *************** Here is the updated facility information *************************");
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
		
        
		System.out.println("\nFacilityClient: *************** Remove a facility from the database *************************");
        facService.removeFacility(11);
        System.out.println("************ Facility Removed ************");
        
        System.out.println("\nFacilityClient: *************** An updated list of all the facilities *************************");
        List<Facility> listOfFacilities = facService.listFacilities();
        for (Facility fac : listOfFacilities) {
        	FacilityDetail facDet = fac.getDetailsAboutFacility();
        	System.out.println("\n\t" + facDet.getName() + " ID: " + fac.getFacilityID());
        }
        

        System.out.println("\nFacilityClient: *************** Request available capacity of a facility *************************");
        //uses sample data
        int roomsAvail = facService.requestAvailableCapacity(fact4);
		System.out.println("There are " + roomsAvail + " rooms currently available at Facility #" + fact4.getFacilityID() + ".");
        
	}
}
