package com.facility.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.temporal.ChronoUnit;

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
	      System.err.println("MaintenanceService: Threw an Exception making a "
	      		+ "maintenance request.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	/***
	 * Schedules a maintenance request by moving the request from the table of 
	 * maintenance requests to the table of completed maintenance.
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
	      System.err.println("MaintenanceService: Threw an Exception calculating "
	      		+ "maintenance cost for facility.");
	      System.err.println(se.getMessage());
	    }
		return 0;
	}
	
	/***
	 * Lists maintenance requests that have yet to be scheduled/completed at a 
	 * particular facility.
	 * @param fac Facility to list the maintenance requests for
	 * @return a list of Maintenance objects containing maintenance requests
	 */
	public List<Maintenance> listMaintRequests(Facility fac) {
		try {
			return maintenanceDAO.listMaintRequests(fac);
		} catch (Exception se) {
			System.err.println("MaintenanceService: Threw an Exception listing "
					+ "maintenance requests.");
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
			System.err.println("MaintenanceService: Threw an Exception listing "
					+ "completed maintenance.");
		    System.err.println(se.getMessage());
		}
		return null;
	}
	
	/***
	 * Lists all problems that have affected a particular facility including 
	 * current maintenance requests and completed maintenance. 
	 * @param fac Facility to the list the problems
	 * @return a list of Maintenance objects which are the problems that have affected a facility
	 */
	public List<Maintenance> listFacilityProblems(Facility fac) {
		List<Maintenance> facilityProblems = new ArrayList<Maintenance>();
		try {
			facilityProblems.addAll(maintenanceDAO.listMaintRequests(fac));
			facilityProblems.addAll(maintenanceDAO.listMaintenance(fac));
			
			//sort problems by cost
			Collections.sort(facilityProblems, new Comparator<Maintenance>() {
				@Override
				public int compare(Maintenance m1, Maintenance m2) {
					return (m2.getCost() > m1.getCost()) ? -1 : 1;
				}
			});
			
			return facilityProblems;		
		} catch (Exception se) {
			System.err.println("MaintenanceService: Threw an Exception "
					+ "listing all facility problems.");
		    System.err.println(se.getMessage());
		}
		return null;
	}
	
	/***
	 * Calculates the down time for a facility with the assumption that each completed
	 * maintenance item required 7 days of down time.
	 * @param fac Facility to calculate the down time
	 * @return down time in days
	 */
	public int calcDownTimeForFacility(Facility fac) {
		int daysOfDownTime = 0;
		try {
			int numberOfCompletedMaintItems = maintenanceDAO.listMaintenance(fac).size();
			daysOfDownTime = numberOfCompletedMaintItems * 7;
		} catch (Exception se) {
			System.err.println("MaintenanceService: Threw an Exception calculating "
					+ "the down time for a facility.");
		    System.err.println(se.getMessage());
		}
		
		return daysOfDownTime;
	}
	
	/***
	 * Calculates the problem rate for a facility by dividing the down time for 
	 * completed maintenance by the number of days since the facility was first created/assigned. 
	 * @param fac Facility to calculate the problem rate for
	 * @return the problem rate of the facility
	 */
	public double calcProblemRateForFacility(Facility fac) {
		UseService useService = new UseService();
		try {
			LocalDate facilityStartDate = useService.getFacilityStartDate(fac);
			double totalDays = ChronoUnit.DAYS.between(facilityStartDate, LocalDate.now());
			return calcDownTimeForFacility(fac) / totalDays;
		} catch (Exception se) {
			System.err.println("MaintenanceService: Threw an Exception calculating "
					+ "the down time for a facility.");
		    System.err.println(se.getMessage());
		}
		
		return 0;
	}
}
