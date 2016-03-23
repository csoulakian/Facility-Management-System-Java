package com.facility.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.facility.base.Facility;
import com.facility.use.*;

public class UseDAO {
	
	public UseDAO() {}
	
	/***
	 * List the inspections at a particular facility.
	 * @param fac facility to search for inspections
	 * @return a list of inspections
	 */
	public List<Inspection> listInspections(Facility fac) {
		
		List<Inspection> listOfInspec = new ArrayList<Inspection>();
		
		try { 		
	    	
    		Statement st = DBHelper.getConnection().createStatement();
	    	String listInspectionsQuery = "SELECT * FROM inspection WHERE facility_id = '" + fac.getFacilityID() + "'";

	    	ResultSet useRS = st.executeQuery(listInspectionsQuery);      
	    	System.out.println("UseDAO: *************** Query " + listInspectionsQuery);
	    	
	    
		    while ( useRS.next() ) {
		    	Inspection inspec = new Inspection();
		    	inspec.setInspection_type(useRS.getString("inspection_type"));
		    	inspec.setInspection_detail(useRS.getString("inspection_detail"));
		    	inspec.setFacility_ID(fac.getFacilityID());
		    	listOfInspec.add(inspec);
		    }
	    	  
	   }	    
	   catch (SQLException se) {
	      System.err.println("UseDAO: Threw a SQLException retreiving inspections from Inspections table.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	   }
		
		
		return listOfInspec;
		
	}
	
	
}
