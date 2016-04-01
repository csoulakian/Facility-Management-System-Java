package com.facility.view;

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
		
		System.out.println("\nMaintenanceClient: *********** Calculate total maintenance cost *****************");
		int totalCost = maintenanceService.calcMaintenanceCostForFacility(fact1);
		System.out.println("The total cost of maintenance already completed at Facility #" + fact1.getFacilityID() + " is $" + totalCost + ".");
		
		
		
	}
	
}
