package com.facility.service;

import java.util.List;

import com.facility.base.Facility;
import com.facility.dal.MaintenanceDAO;
import com.facility.maintenance.Maintenance;

public class MaintenanceService {

	private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();
	
	/***
	 * Creates a new maintenance request specifying the facility, maintenance details, and the cost.
	 * @param fac Facility where the maintenance request is made
	 * @param maintenanceDetails description of the maintenance requested
	 * @param cost of maintenance
	 * @return the Maintenance object that was requested
	 */
	public Maintenance makeFacilityMaintRequest(Facility fac, String maintenanceDetails, int cost) {
		try {
			return maintenanceDAO.makeFacilityMaintRequest(fac, maintenanceDetails, cost);
	    } catch (Exception se) {
	      System.err.println("MaintenanceService: Threw an Exception making a maintenance request.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	/***
	 * Schedules a maintenance request by moving the request from the table of maintenance requests
	 * to the table of completed maintenance.
	 * @param maintRequest the request to be scheduled
	 */
	public void scheduleMaintenance(Maintenance maintRequest) {
		try {
			maintenanceDAO.scheduleMaintenance(maintRequest);
	    } catch (Exception se) {
	      System.err.println("MaintenanceService: Threw an Exception scheduling maintenance.");
	      System.err.println(se.getMessage());
	    }
	}
	
	/***
	 * Calculates the total cost of completed maintenance for a particular facility.
	 * @param fac Facility to calculate maintenance cost
	 * @return total cost of completed maintenance
	 */
	public int calcMaintenanceCostForFacility(Facility fac) {
		try {
			return maintenanceDAO.calcMaintenanceCostForFacility(fac);
	    } catch (Exception se) {
	      System.err.println("MaintenanceService: Threw an Exception calculating maintenance cost for facility.");
	      System.err.println(se.getMessage());
	    }
		return 0;
	}
	
	/***
	 * Lists maintenance requests that have yet to be scheduled/completed at a particular facility.
	 * @param fac Facility to list the maintenance requests for
	 * @return a list of Maintenance objects containing maintenance requests
	 */
	public List<Maintenance> listMaintRequests(Facility fac) {
		try {
			return maintenanceDAO.listMaintRequests(fac);
		} catch (Exception se) {
			System.err.println("MaintenanceService: Threw an Exception listing maintenance requests.");
		    System.err.println(se.getMessage());
		}
		return null;
	}
	
	/***
	 * Lists maintenance that has already been completed at a particular facility.
	 * @param fac Facility to list maintenance for
	 * @return a list of Maintenance objects containing completed maintenance
	 */
	public List<Maintenance> listMaintenance(Facility fac) {
		try {
			return maintenanceDAO.listMaintenance(fac);
		} catch (Exception se) {
			System.err.println("MaintenanceService: Threw an Exception listing completed maintenance.");
		    System.err.println(se.getMessage());
		}
		return null;
	}
	
	
}
