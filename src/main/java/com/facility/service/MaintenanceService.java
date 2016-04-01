package com.facility.service;

import com.facility.base.Facility;
import com.facility.dal.MaintenanceDAO;
import com.facility.maintenance.Maintenance;

public class MaintenanceService {

	private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();
	
	public Maintenance makeFacilityMaintRequest(Facility fac, String maintenanceDetails, int cost) {
		try {
			return maintenanceDAO.makeFacilityMaintRequest(fac, maintenanceDetails, cost);
	    } catch (Exception se) {
	      System.err.println("MaintenanceService: Threw an Exception making a maintenance request.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	public void scheduleMaintenance(Maintenance maint) {
		try {
			maintenanceDAO.scheduleMaintenance(maint);
	    } catch (Exception se) {
	      System.err.println("MaintenanceService: Threw an Exception scheduling maintenance.");
	      System.err.println(se.getMessage());
	    }		
	}
	
	
}
