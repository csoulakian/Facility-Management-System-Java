package com.facility.service;

import java.util.List;
import java.time.*;

import com.facility.dal.UseDAO;
import com.facility.use.*;
import com.facility.base.*;

public class UseService {

	private UseDAO useDAO = new UseDAO();
	
	/***
	 * List all the inspections at a particular facility.
	 * Uses sample dummy data in database.
	 * @param fac facility to search for inspections
	 * @return a list of inspections
	 */
	public List<Inspection> listInspections(Facility fac) {
		try {
			return useDAO.listInspections(fac);
	    } catch (Exception se) {
	      System.err.println("UseService: Threw an Exception retrieving list of inspections.");
	      System.err.println(se.getMessage());
	    }
		
		return null;
	}
	
	/***
	 * Checks if a particular room at a facility is in use during an interval.
	 * Enter 0 for room number if checking the entire facility.
	 * @param fac Facility to be checked
	 * @param roomNumber room number to be checked (0 if entire facility)
	 * @param startDate
	 * @param endDate
	 * @return true if facility/room is in use, otherwise false
	 */
	public boolean isInUseDuringInterval(FacilityUse facUse) {
		//ensures the start and end data are valid and room number exists
		if (facUse.getStartDate().isAfter(facUse.getEndDate())) {
			System.out.println("Start date must be before end date.");
		} else if (facUse.getRoomNumber() > facUse.getDetailsAboutFacility().getNumberOfRooms()) {
			System.out.println("Invalid room number. There are only " + 
					facUse.getDetailsAboutFacility().getNumberOfRooms() + 
					" rooms at this facility.");
		} else {
			try {
				return useDAO.isInUseDuringInterval(facUse);
		    } catch (Exception se) {
		      System.err.println("UseService: Threw an Exception checking if facility is in use during interval.");
		      System.err.println(se.getMessage());
		    }
		}
		
		return true;
	}
	
	/***
	 * Assigns a facility and room number to use from a particular start date to a particular end date.
	 * Enter room number 0 if assigning entire facility to use.
	 * @param fac facility being assigned to use
	 * @param roomNumber room number being assigned to use (0 if assigning entire facility)
	 * @param startDate beginning date of use
	 * @param endDate ending date of use
	 */
	public void assignFacilityToUse(FacilityUse facUse) {
		
		//ensures the start and end data are valid, room number exists, and room isn't already in use at that time
		if (facUse.getStartDate().isAfter(facUse.getEndDate())) {
			System.out.println("Start date must be before end date.");
		} else if (facUse.getRoomNumber() > facUse.getDetailsAboutFacility().getNumberOfRooms()) {
			System.out.println("Invalid room number. There are only " + 
					facUse.getDetailsAboutFacility().getNumberOfRooms() + 
					" rooms at this facility.");
		} else if (isInUseDuringInterval(facUse)) {
			System.out.println("This room at the facility is already in use during this interval.");
		} else {
			try {
				useDAO.assignFacilityToUse(facUse);
		    } catch (Exception se) {
		      System.err.println("UseService: Threw an Exception assigning a facility to use.");
		      System.err.println(se.getMessage());
		    }
		}
		
	}
	
	/***
	 * Lists the usage assignments at a particular facility first by room number and then by date.
	 * @param fac Facility to list the usage assignments for
	 * @return a list of FacilityUse objects containing a room number, start date, and end date.
	 */
	public List<FacilityUse> listActualUsage(Facility fac) {
		try {
			return useDAO.listActualUsage(fac);
	    } catch (Exception se) {
	      System.err.println("UseService: Threw an Exception retrieving list of usage.");
	      System.err.println(se.getMessage());
	    }
		
		return null;
	}
	
}
