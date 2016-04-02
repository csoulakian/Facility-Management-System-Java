package com.facility.view;

import java.util.List;

import com.facility.base.Facility;
import com.facility.base.FacilityDetail;
import com.facility.maintenance.Maintenance;
import com.facility.service.MaintenanceService;

public class MaintenanceClient {

	public MaintenanceClient() throws Exception {
		
		MaintenanceService maintenanceService = new MaintenanceService();
		
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
		
		
		/*System.out.println("\nMaintenanceClient: *********** Creating new facility maintenance request *****************");
		Maintenance maintenance = maintenanceService.makeFacilityMaintRequest(fact1, "testing maintenance", 100);
		System.out.println("\nMaintenanceClient: *********** Maintenance request created *****************");
		
		
		System.out.println("\nMaintenanceClient: *********** Scheduling this maintenance request *****************");
		maintenanceService.scheduleMaintenance(maintenance);
		System.out.println("\nMaintenanceClient: *********** Maintenance request scheduled *****************");*/
		
		System.out.println("\nMaintenanceClient: *********** Calculate total maintenance cost of a facility *****************");
		int totalCost = maintenanceService.calcMaintenanceCostForFacility(fact1);
		System.out.println("The total cost of maintenance already completed at Facility #" + fact1.getFacilityID() + " is $" + totalCost + ".");
		
		
		//uses sample data to list maintenance requests, formatted as a table
		System.out.println("\nMaintenanceClient: *********** List current maintenance requests at a facility *****************");
		List<Maintenance> maintRequestList = maintenanceService.listMaintRequests(fact3);
		Object[][] requests = new Object[maintRequestList.size() + 1][2];
		requests[0] = new Object[] {"Maintenance Request Details", "Cost"};
		for (int i = 1; i <= maintRequestList.size(); i++) {
			requests[i] = new Object[] {maintRequestList.get(i-1).getDetails(), maintRequestList.get(i-1).getCost()};
		}
		System.out.println("Current maintenance requests at Facility #" + fact3.getFacilityID() + ":");
		for (Object[] row : requests) {
			System.out.format("   %-29s%6s\n", row);
		}
		
		//uses sample data to list completed maintenance, formatted as a table
		System.out.println("\nMaintenanceClient: *********** List maintenance completed at a facility *****************");
		List<Maintenance> maintenanceList = maintenanceService.listMaintenance(fact3);
		Object[][] maintenance = new Object[maintenanceList.size() + 1][2];
		maintenance[0] = new Object[] {"Maintenance Details", "Cost"};
		for (int i = 1; i <= maintenanceList.size(); i++) {
			maintenance[i] = new Object[] {maintenanceList.get(i-1).getDetails(), maintenanceList.get(i-1).getCost()};
		}
		System.out.println("Maintenance completed at Facility #" + fact3.getFacilityID() + ":");
		for (Object[] row : maintenance) {
			System.out.format("   %-30s%6s\n", row);
		}
		
		//uses sample data to list facility problems, formatted as a table
		System.out.println("\nMaintenanceClient: *********** List all problems that have affected a facility *****************");
		List<Maintenance> facilityProblemsList = maintenanceService.listFacilityProblems(fact3);
		Object[][] problems = new Object[facilityProblemsList.size() + 1][2];
		problems[0] = new Object[] {"Problem Details", "Cost"};
		for (int i = 1; i <= facilityProblemsList.size(); i++) {
			problems[i] = new Object[] {facilityProblemsList.get(i-1).getDetails(), facilityProblemsList.get(i-1).getCost()};
		}
		System.out.println("Problems at Facility #" + fact3.getFacilityID() + ":");
		for (Object[] row : problems) {
			System.out.format("   %-30s%6s\n", row);
		}
		
		System.out.println("\nMaintenanceClient: *********** Calculate the down time for a facility *****************");
		int downTime = maintenanceService.calcDownTimeForFacility(fact3);
		System.out.println("Facility #" + fact3.getFacilityID() + " was down for maintenance for " + downTime + " days total, "
				+ "assuming each completed maintenance request took 7 days to complete.");
	
		System.out.println("\nMaintenanceClient: *********** Calculate the problem rate for a facility *****************");
		double problemRate = maintenanceService.calcProblemRateForFacility(fact3) * 100;
		System.out.print("\nThe problem rate at Facility #" + fact3.getFacilityID() + " is ");
		System.out.format("%.2f", problemRate);
		System.out.print("%.");
				
	}
	
}
