package com.facility.service;

import java.util.List;

import com.facility.dal.UseDAO;
import com.facility.use.*;
import com.facility.base.*;

public class UseService {

	private UseDAO useDAO = new UseDAO();
	
	/***
	 * List all the inspections at a particular facility.
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
	
}
