package com.facility.view;

import java.time.LocalDate;
import java.util.List;

import com.facility.base.*;
import com.facility.service.FacilityService;
import com.facility.service.UseService;
import com.facility.use.FacilityUse;
import com.facility.use.Inspection;

public class UseClient {

	public UseClient() throws Exception {
		
		UseService useService = new UseService();
		FacilityService facilityService = new FacilityService();
		
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
		
		System.out.println("\nUseClient: *************** Listing the inspections at a facility *************************");
		
		//uses sample dummy data for inspections in database
		System.out.println("\n\tInspections At Facility #" + fact7.getFacilityID());
		for (Inspection inspec : useService.listInspections(fact7)) {
			System.out.println("\t" + inspec.getInspection_type() +
					" status: " + inspec.getInspection_detail());
		}
		
		//set up new facility 12 to be checked if it's in use during an interval
		FacilityUse factUse = new FacilityUse();;
		factUse.setFacilityID(12);
		FacilityDetail factDet = new FacilityDetail();
		factDet.setNumberOfRooms(6);
		factDet.setName("Test Facility");
		factDet.setFacilityID(12);
		factUse.setDetailsAboutFacility(factDet);
		facilityService.addNewFacility(factUse);
		factUse.setStartDate(LocalDate.of(2015, 12, 1));
		factUse.setEndDate(LocalDate.of(2017, 12, 1));
		factUse.setRoomNumber(1);
		
		System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");

		boolean result = useService.isInUseDuringInterval(factUse);

		System.out.print("\tFacility #" + factUse.getFacilityID());
		if (factUse.getRoomNumber() != 0) {
			System.out.print(" - Room " + factUse.getRoomNumber());
		}
		if (result) {
			System.out.print(" IS ");
		} else {
			System.out.print(" is NOT ");
		}
		System.out.print("in use from " + factUse.getStartDate() + " to " + factUse.getEndDate() + ".\n");
		
		//assign the facility to use during the previously checked room and start/end date
		System.out.println("\nUseClient: ************ Assigning a facility to use ***************");
		useService.assignFacilityToUse(factUse);
		System.out.println("\nUseClient: ************ Facility and room assigned ***************");
		
		//re-check if facility is in use
		System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");

		boolean result2 = useService.isInUseDuringInterval(factUse);

		System.out.print("\tFacility #" + factUse.getFacilityID());
		if (factUse.getRoomNumber() != 0) {
			System.out.print(" - Room " + factUse.getRoomNumber());
		}
		if (result2) {
			System.out.print(" IS ");
		} else {
			System.out.print(" is NOT ");
		}
		System.out.print("in use from " + factUse.getStartDate() + " to " + factUse.getEndDate() + ".\n");
		

		//list actual usage that has been assigned to a particular facility
		System.out.println("\nUseClient: ************ Listing the usage at a facility before being vacated***************");
		
		//uses sample dummy data of usage in database
		List<FacilityUse> usageList = useService.listActualUsage(factUse);
		Object[][] usage = new Object[usageList.size() + 1][3];
		usage[0] = new Object[] {"Room #", "Start Date", "End Date"};
		for (int i = 1; i <= usageList.size(); i++) {
			usage[i] = new Object[] {usageList.get(i-1).getRoomNumber(), usageList.get(i-1).getStartDate().toString(), 
					usageList.get(i-1).getEndDate().toString()};
			if ((int) usage[i][0] == 0) {
				usage[i][0] = "all";
			}
		}
		System.out.println("Usage at Facility #" + factUse.getFacilityID());
		for (Object[] row : usage) {
			System.out.format("\t%-10s%-15s%-15s\n", row);
		}
		
		System.out.println("\nUseClient: ************ Vacate a facility  ***************");
		useService.vacateFacility(factUse, 1);
		System.out.println("\nUseClient: ************ Facility vacated  ***************");
		
		//list actual usage that has been assigned to a particular facility
		System.out.println("\nUseClient: ************ Listing the usage at a facility after being vacated***************");
		
		//uses sample dummy data of usage in database
		List<FacilityUse> usageList2 = useService.listActualUsage(factUse);
		Object[][] usage2 = new Object[usageList2.size() + 1][3];
		usage2[0] = new Object[] {"Room #", "Start Date", "End Date"};
		for (int i = 1; i <= usageList2.size(); i++) {
			usage2[i] = new Object[] {usageList2.get(i-1).getRoomNumber(), usageList2.get(i-1).getStartDate().toString(), 
					usageList2.get(i-1).getEndDate().toString()};
			if ((int) usage2[i][0] == 0) {
				usage2[i][0] = "all";
			}
		}
		System.out.println("Usage at Facility #" + factUse.getFacilityID());
		for (Object[] row : usage2) {
			System.out.format("\t%-10s%-15s%-15s\n", row);
		}
		
		
		
		//calculate current usage rate of a facility
		System.out.println("\nUseClient: ************ Calculating the current usage rate at a facility ***************");
		int usageRate = (int) (useService.calcUsageRate(fact3) * 100);
		System.out.println("Current usage at Facility #" + fact3.getFacilityID() + " is " + usageRate + "%.");
		
		
		//remove this new facility 12 for next time UseClient is run
		facilityService.removeFacility(12);

	}
	
	
}
