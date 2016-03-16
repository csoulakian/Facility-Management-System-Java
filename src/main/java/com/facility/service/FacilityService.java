package com.facility.service;

import com.facility.dal.FacilityDAO;
import com.facility.base.Facility;

public class FacilityService {
	
	private FacilityDAO facDAO = new FacilityDAO();
	
	//Insert a new facility in the DB
	public void addNewFacility(Facility facility) {
		try {
			facDAO.addNewFacility(facility);
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw an Exception adding new facility.");
	      System.err.println(se.getMessage());
	    }
	}
	
	public Facility getFacilityInformation(int id) {
		
		try {
			Facility fac = facDAO.getFacilityInformation(id);
	    	return fac;
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw an Exception retrieving facility.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	public void removeFacility(int id) {
		
		try {
			facDAO.removeFacility(id);
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw an Exception removing facility.");
	      System.err.println(se.getMessage());
	    }
	}
}
