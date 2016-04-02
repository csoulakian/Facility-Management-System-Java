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
	    	String listInspectionsQuery = "SELECT * FROM inspection WHERE "
	    			+ "facility_id = '" + fac.getFacilityID() + "'";

	    	ResultSet useRS = st.executeQuery(listInspectionsQuery);      
	    	System.out.println("UseDAO: *************** Query " + listInspectionsQuery + "\n");
	    	
		    while ( useRS.next() ) {
		    	Inspection inspec = new Inspection();
		    	inspec.setInspection_type(useRS.getString("inspection_type"));
		    	inspec.setInspection_detail(useRS.getString("inspection_detail"));
		    	inspec.setFacility_ID(fac.getFacilityID());
		    	listOfInspec.add(inspec);
		    }
		    
		    //close to manage resources
		    useRS.close();
		    st.close();
	    	  
	   }	    
	   catch (SQLException se) {
		   System.err.println("UseDAO: Threw a SQLException retreiving "
		   		+ "inspections from Inspections table.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
	   }
		
		return listOfInspec;
		
	}
	
	/***
	 * Checks if a facility is in use during a particular interval by comparing the input 
	 * parameter to the use table. Use room number 0 if checking entire facility.
	 * When checking if entire facility is in use, but only some rooms are in use, will return false.
	 * @param facUse instance of facUse to be checked which indicates the room number and start/end dates
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
	    	
	    	//close to manage resources
	    	useRS.close();
	    	st.close();
	    	
        }
        catch (SQLException se) {
        	System.err.println("UseDAO: Threw a SQLException checking if "
        			+ "facility is in use during an interval.");
        	System.err.println(se.getMessage());
        	se.printStackTrace();
        }
		
		return result;
        
	}
	
	
	/***
	 * Assigns a facility to use by adding it to the use table.
	 * UseService has already confirmed validity of start and end date, existence of 
	 * room number, and if room is already in use during this interval.
	 * Room number 0 indicates the entire facility is being assigned to use.
	 * @param facUse instance of FacilityUse to be assigned which indicates the room number and start/end dates
	 */
	public void assignFacilityToUse(FacilityUse facUse) {
		
		Connection con = DBHelper.getConnection();
        PreparedStatement usePst = null;
        
        try {
        	//Insert the facility ID, room number, and start/end dates into use table
            String useStm = "INSERT INTO use (facility_id, room_number, start_date, "
            		+ "end_date) VALUES (?, ?, ?, ?)";
            usePst = con.prepareStatement(useStm);
            usePst.setInt(1, facUse.getFacilityID());
            usePst.setInt(2, facUse.getRoomNumber());
            usePst.setDate(3, Date.valueOf(facUse.getStartDate()));
            usePst.setDate(4, Date.valueOf(facUse.getEndDate()));
            usePst.executeUpdate();
            System.out.println("UseDAO: *************** Query " + usePst + "\n");
            
            //close to manage resources
            usePst.close();
            con.close();
        }
        catch (SQLException se) {
        	System.err.println("UseDAO: Threw a SQLException assigning a facility "
        			+ "to use in the use table.");
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
	    	String listUsageQuery = "SELECT * FROM use WHERE facility_id = '" + 
	    			fac.getFacilityID() + "' ORDER BY room_number, start_date";

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
		    
		    //close to manage resources
		    useRS.close();
		    st.close();
		    return listOfUsage;
	    	  
	    }	    
	    catch (SQLException se) {
	    	System.err.println("UseDAO: Threw a SQLException retreiving list of usage from use table.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    }
		
		return null;
		
	}
	
	/***
	 * Vacates a facility/room by setting the end date of a current assignment to yesterday. 
	 * This allows the facility to be reassigned starting today.
	 * @param fac Facility to vacate
	 * @param roomNumber room to vacate (0 if it's the entire facility)
	 */
	public void vacateFacility(Facility fac, int roomNumber) {
		
		try {
		
			Statement st = DBHelper.getConnection().createStatement();
			String vacateQuery = "";
			
			List<FacilityUse> usageList = listActualUsage(fac);
			for (FacilityUse use : usageList) {
				//TODO these checks are also done in UseService. can they be removed from DAO method?
				//if room number matches usage list and room is currently in use, set vacateQuery
				if ((use.getRoomNumber() == roomNumber || use.getRoomNumber() == 0) & ((LocalDate.now().equals(use.getStartDate()) || 
						LocalDate.now().isAfter(use.getStartDate())) & LocalDate.now().equals(use.getEndDate()) || 
						LocalDate.now().isBefore(use.getEndDate()))) {
					vacateQuery = "UPDATE use SET end_date = '" + Date.valueOf(LocalDate.now().minusDays(1)) + 
							"' WHERE facility_id = " + fac.getFacilityID() + "AND room_number = " + roomNumber +
							"AND start_date = '" + Date.valueOf(use.getStartDate()) + "'";
				}
			}
			
			st.execute(vacateQuery);
			System.out.println("UseDAO: *************** Query " + vacateQuery + "\n");
			
		}
		catch (SQLException se){
			System.err.println("UseDAO: Threw a SQLException vacating the facility.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
		}
		
	}
	
	/***
	 * Gets the creation date of a facility, which is the earliest start date 
	 * assigned in the use table for that facility.
	 * @param fac Facility to get the start date
	 * @return start date for the facility
	 */
	public LocalDate getFacilityStartDate(Facility fac) {
		
		LocalDate facilityStartDate = null;
		try { 		
	    	
    		Statement st = DBHelper.getConnection().createStatement();
	    	String getFacilityStartDateQuery = "SELECT start_date FROM use WHERE facility_id = '" +
	    			fac.getFacilityID() + "' ORDER BY start_date LIMIT 1";

	    	ResultSet useRS = st.executeQuery(getFacilityStartDateQuery);      
	    	System.out.println("UseDAO: *************** Query " + getFacilityStartDateQuery + "\n");
	    	
		    while ( useRS.next() ) {
		    	facilityStartDate = useRS.getDate("start_date").toLocalDate();
		    }
		    
		    //close to manage resources
		    useRS.close();
		    st.close();
	    	  
	    }
	    catch (SQLException se) {
	    	System.err.println("UseDAO: Threw a SQLException retreiving facility start date "
	    			+ "from the use table.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    }
		
		return facilityStartDate;
	}
	
	
}
