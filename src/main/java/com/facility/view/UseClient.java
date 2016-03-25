package com.facility.view;

import java.time.LocalDate;
import java.util.List;

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
		
		
		//list actual usage that has been assigned to a particular facility
		System.out.println("\nUseClient: ************ Listing the usage at a facility ***************");
		
		//uses sample dummy data of usage in database
		Facility fac = new Facility();
		fac.setFacilityID(1);
		List<FacilityUse> usageList = useService.listActualUsage(fac);
		Object[][] usage = new Object[usageList.size() + 1][3];
		usage[0] = new Object[] {"Room #", "Start Date", "End Date"};
		for (int i = 1; i <= usageList.size(); i++) {
			usage[i] = new Object[] {usageList.get(i-1).getRoomNumber(), usageList.get(i-1).getStartDate().toString(), 
					usageList.get(i-1).getEndDate().toString()};
			if ((int) usage[i][0] == 0) {
				usage[i][0] = "all";
			}
		}
		System.out.println("\nUsage at Facility #" + fac.getFacilityID());
		for (Object[] row : usage) {
			System.out.format("\t%-10s%-15s%-15s\n", row);
		}
		
	}
	
	
}
