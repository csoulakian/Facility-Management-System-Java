package com.facility.view;

import java.time.LocalDate;

import com.facility.base.*;
import com.facility.service.UseService;
import com.facility.use.Inspection;

public class UseClient {

	public UseClient() throws Exception {
		
		UseService useService = new UseService();
		
		//set up facility to list inspections for
		Facility factIns = new Facility();;
		factIns.setFacilityID(7);

		System.out.println("\nUseClient: *************** Listing the inspections at a facility *************************");
		
		//uses sample dummy data for inspections in database
		System.out.println("\n\tInspections At Facility #" + factIns.getFacilityID());
		for (Inspection inspec : useService.listInspections(factIns)) {
			System.out.println("\t" + inspec.getInspection_type() +
					" status: " + inspec.getInspection_detail());
		}
		
		//set up facility 12 to be checked if it's in use during an interval
		Facility fact = new Facility();;
		fact.setFacilityID(12);
		FacilityDetail factDet = new FacilityDetail();
		factDet.setNumberOfRooms(6);
		fact.setDetailsAboutFacility(factDet);
		LocalDate checkStart = LocalDate.of(2014, 12, 1);
		LocalDate checkEnd = LocalDate.of(2014, 12, 7);
		int checkRoom = 1;
		
		System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");

		boolean result = useService.isInUseDuringInterval(fact, checkRoom, checkStart, checkEnd);

		System.out.print("\n\tFacility #" + fact.getFacilityID());
		if (checkRoom != 0) {
			System.out.print(" - Room " + checkRoom);
		}
		if (result) {
			System.out.print(" IS ");
		} else {
			System.out.print(" is NOT ");
		}
		System.out.print("in use from " + checkStart + " to " + checkEnd + ".");
		
		
		
		//set up for assigning facility to use
		LocalDate assignStart = LocalDate.of(2014, 12, 1);
		LocalDate assignEnd = LocalDate.of(2014, 12, 7);
		int useRoom = 1;
		
		System.out.println("\nUseClient: ************ Assigning a facility to use ***************");
		useService.assignFacilityToUse(fact, useRoom, assignStart, assignEnd);
		System.out.println("\nUseClient: ************ Facility and room assigned ***************");
		
		
		System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");

		
		boolean result2 = useService.isInUseDuringInterval(fact, useRoom, assignStart, assignEnd);

		System.out.print("\n\tFacility #" + fact.getFacilityID());
		if (useRoom != 0) {
			System.out.print(" - Room " + useRoom);
		}
		if (result2) {
			System.out.print(" IS ");
		} else {
			System.out.print(" is NOT ");
		}
		System.out.print("in use from " + checkStart + " to " + checkEnd + ".");
		
		
	}
	
	
}
