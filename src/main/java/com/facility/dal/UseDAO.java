package com.facility.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import com.facility.base.Facility;
import com.facility.use.*;

public class UseDAO {
	
	public UseDAO() {}
	
	/***
	 * List the inspections at a particular facility.
	 * Uses sample dummy data in database.
	 * @param fac facility to search for inspections
	 * @return a list of inspections
	 */
	public List<Inspection> listInspections(Facility fac) {
		
		List<Inspection> listOfInspec = new ArrayList<Inspection>();
		
		try { 		
	    	
    		Statement st = DBHelper.getConnection().createStatement();
	    	String listInspectionsQuery = "SELECT * FROM inspection WHERE facility_id = '" + fac.getFacilityID() + "'";

	    	ResultSet useRS = st.executeQuery(listInspectionsQuery);      
	    	System.out.println("UseDAO: *************** Query " + listInspectionsQuery + "\n");
	    	
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
	
	/***
	 * Checks if a facility is in use during a particular interval by comparing the input 
	 * parameters to the use table. Use room number 0 if checking entire facility.
	 * When checking if entire facility is in use, but only some rooms are in use, will return false.
	 * @param fac facility to be checked
	 * @param roomNumber room number to be checked (0 if entire facility)
	 * @param startDate
	 * @param endDate
	 * @return true if facility/room is in use, otherwise false
	 */
	public boolean isInUseDuringInterval(FacilityUse facUse) {
		
		boolean result = false;
        try {
        	//Insert the facility ID, room number, and start/end dates into use table
            Statement st = DBHelper.getConnection().createStatement();
	    	String selectUseAssignments = "SELECT * FROM use WHERE facility_id = " + facUse.getFacilityID() + 
	    			" AND room_number IN (0, " + facUse.getRoomNumber() + ")";

	    	ResultSet useRS = st.executeQuery(selectUseAssignments);      
	    	System.out.println("UseDAO: *************** Query " + selectUseAssignments + "\n");
	    	
	    	//check if dates in database overlap with input interval
	    	while (useRS.next()) {
	    		LocalDate assignStart = useRS.getDate("start_date").toLocalDate();
	    		LocalDate assignEnd = useRS.getDate("end_date").toLocalDate();
	    		if (facUse.getStartDate().isBefore(assignEnd) && (assignStart.isBefore(facUse.getEndDate()) || 
	    				assignStart.equals(facUse.getEndDate()))) {
	    			result = true;
	    			break;
	    		}
	    	}
	    	
        }
        catch (SQLException se) {
        	System.err.println("UseDAO: Threw a SQLException checking if facility is in use during an interval.");
        	System.err.println(se.getMessage());
        	se.printStackTrace();
        }
		
		return result;
        
	}
	
	
	/***
	 * Assigns a facility to use by adding it to the use table.
	 * UseService has already confirmed validity of start and end date, existence of room number, 
	 * and if room is already in use during this interval.
	 * Room number 0 indicates the entire facility is being assigned to use.
	 * @param fac Facility being assigned to use
	 * @param roomNumber room being assigned to use. 0 = entire facility
	 * @param startDate beginning date facility will be in use
	 * @param endDate ending date facility will be in use
	 */
	public void assignFacilityToUse(FacilityUse facUse) {
		
		Connection con = DBHelper.getConnection();
        PreparedStatement usePst = null;
        
        try {
        	//Insert the facility ID, room number, and start/end dates into use table
            String useStm = "INSERT INTO use (facility_id, room_number, start_date, end_date) VALUES (?, ?, ?, ?)";
            usePst = con.prepareStatement(useStm);
            usePst.setInt(1, facUse.getFacilityID());
            usePst.setInt(2, facUse.getRoomNumber());
            usePst.setDate(3, Date.valueOf(facUse.getStartDate()));
            usePst.setDate(4, Date.valueOf(facUse.getEndDate()));
            usePst.executeUpdate();
            System.out.println("UseDAO: *************** Query " + usePst + "\n");
        }
        catch (SQLException se) {
        	System.err.println("UseDAO: Threw a SQLException assigning a facility to use in the use table.");
        	System.err.println(se.getMessage());
        	se.printStackTrace();
        }
		
	}
	
	/***
	 * Lists all the usage assignments at a particular facility from the use table.
	 * Usage assignments are first sorted by room number and then by date.
	 * @param fac Facility to list the usage assignments for
	 * @return a list of FacilityUse objects containing a room number, start date, and end date
	 */
	public List<FacilityUse> listActualUsage(Facility fac) {
		
		List<FacilityUse> listOfUsage = new ArrayList<FacilityUse>();
		
		try { 		
	    	
    		Statement st = DBHelper.getConnection().createStatement();
	    	String listUsageQuery = "SELECT * FROM use WHERE facility_id = '" + fac.getFacilityID() + "' ORDER BY room_number, start_date";

	    	ResultSet useRS = st.executeQuery(listUsageQuery);      
	    	System.out.println("UseDAO: *************** Query " + listUsageQuery + "\n");
	    	
		    while ( useRS.next() ) {
		    	FacilityUse use = new FacilityUse();
		    	use.setFacilityID(fac.getFacilityID());
		    	use.setRoomNumber(useRS.getInt("room_number"));
		    	use.setStartDate(useRS.getDate("start_date").toLocalDate());
		    	use.setEndDate(useRS.getDate("end_date").toLocalDate());
		    	listOfUsage.add(use);
		    }
	    	  
	    }	    
	    catch (SQLException se) {
	    	System.err.println("UseDAO: Threw a SQLException retreiving list of usage from use table.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    }
		
		
		return listOfUsage;
		
	}
	
	
}
