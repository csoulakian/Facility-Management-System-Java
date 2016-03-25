package com.facility.view;

import java.time.LocalDate;

import com.facility.base.*;
import com.facility.service.UseService;
import com.facility.use.FacilityUse;
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
		FacilityUse factUse = new FacilityUse();;
		factUse.setFacilityID(12);
		FacilityDetail factDet = new FacilityDetail();
		factDet.setNumberOfRooms(6);
		factUse.setDetailsAboutFacility(factDet);
		factUse.setStartDate(LocalDate.of(2014, 12, 1));
		factUse.setEndDate(LocalDate.of(2014, 12, 7));
		factUse.setRoomNumber(1);
		
		System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");

		boolean result = useService.isInUseDuringInterval(factUse);

		System.out.print("\n\tFacility #" + factUse.getFacilityID());
		if (factUse.getRoomNumber() != 0) {
			System.out.print(" - Room " + factUse.getRoomNumber());
		}
		if (result) {
			System.out.print(" IS ");
		} else {
			System.out.print(" is NOT ");
		}
		System.out.print("in use from " + factUse.getStartDate() + " to " + factUse.getEndDate() + ".");
		
		//assign the facility to use during the previously checked room and start/end date
		System.out.println("\nUseClient: ************ Assigning a facility to use ***************");
		useService.assignFacilityToUse(factUse);
		System.out.println("\nUseClient: ************ Facility and room assigned ***************");
		
		//re-check if facility is in use
		System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");

		
		boolean result2 = useService.isInUseDuringInterval(factUse);

		System.out.print("\n\tFacility #" + factUse.getFacilityID());
		if (factUse.getRoomNumber() != 0) {
			System.out.print(" - Room " + factUse.getRoomNumber());
		}
		if (result2) {
			System.out.print(" IS ");
		} else {
			System.out.print(" is NOT ");
		}
		System.out.print("in use from " + factUse.getStartDate() + " to " + factUse.getEndDate() + ".");
		
		
	}
	
	
}
