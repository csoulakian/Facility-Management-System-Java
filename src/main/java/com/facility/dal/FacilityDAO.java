package com.facility.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.facility.base.*;

public class FacilityDAO {

	public FacilityDAO() {}
	
	/***
	 * Removes facility from the facility table, facility_detail table, and use table.
	 * @param ID of facility to be removed
	 */
	public void removeFacility(int ID) {
		
	    try { 		
	    	//remove from use table
		    Statement st = DBHelper.getConnection().createStatement();
	    	String removeFacilityUseQuery = "delete from use where facility_id = '" + ID + "'";
            st.execute(removeFacilityUseQuery);
    
	    	System.out.println("FacilityDAO: *************** Query " + removeFacilityUseQuery + "\n");
	    	//close to manage resources
	    	st.close();
	    }	    
	    catch (SQLException se) {
	    	System.err.println("FacilityDAO: Threw a SQLException removing the Facility from Use table.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    }
		
	    try { 		
	    	//remove from facility_detail table
		    Statement st = DBHelper.getConnection().createStatement();
	    	String removeFacilityDetailQuery = "delete from facility_detail where facility_id = '" + ID + "'";
            st.execute(removeFacilityDetailQuery);
    
	    	System.out.println("FacilityDAO: *************** Query " + removeFacilityDetailQuery + "\n");
	    	//close to manage resources
	    	st.close(); 
	    }	    
	    catch (SQLException se) {
	    	System.err.println("FacilityDAO: Threw a SQLException removing the Facility Detail from Facility Detail table.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    }
	    
	    try {
	    	//remove from facility table
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String removeFacilityQuery = "delete from facility where id = '" + ID + "'";
            st.execute(removeFacilityQuery);

	    	System.out.println("FacilityDAO: *************** Query " + removeFacilityQuery + "\n");
	    	//close to manage resources
	    	st.close();		  
	    }	    
	    catch (SQLException se) {
	    	System.err.println("FacilityDAO: Threw a SQLException removing the Facility object from Facility table.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    }
	 	
	}
	
	/***
	 * Returns detail information about a facility, including its name, facility ID, number of rooms, 
	 * and a phone number if it's listed.
	 * @param ID of facility
	 * @return facility detail information
	 */
	public Facility getFacilityInformation(int ID) {
	 	 
	    try {
	    	
	    	Facility fac1 = new Facility();
	    	fac1.setFacilityID(ID);
		      	    		  
		    //Get details about facility
	    	Statement st = DBHelper.getConnection().createStatement();
		    String selectDetailQuery = "SELECT name,facility_id,number_of_rooms,phone FROM facility_detail WHERE facility_id = '" + ID + "'";
		    ResultSet detRS = st.executeQuery(selectDetailQuery);
	    	FacilityDetail detail = new FacilityDetail();
	    	  
	    	System.out.println("FacilityDAO: *************** Query " + selectDetailQuery + "\n");
	    	  
		    while ( detRS.next() ) {
		    	detail.setName(detRS.getString("name"));
		    	detail.setFacilityID(detRS.getInt("facility_id"));
		    	detail.setNumberOfRooms(detRS.getInt("number_of_rooms"));
		    	if (detRS.getInt("phone") != 0) {
		    		detail.setPhoneNumber(detRS.getInt("phone"));
		    	}
		    }
		      
		    fac1.setDetailsAboutFacility(detail);
		    
		    //close to manage resources
		    detRS.close();
		      
		    return fac1;
	    }
	    
	    catch (SQLException se) {
	    	System.err.println("FacilityDAO: Threw a SQLException retrieving the Facility object.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    }
	    
	    return null;
	}
	
	/***
	 * Add a new facility to the facility table and details about the facility to facility_detail table.
	 * Must create a new facility object before running addNewFacility method.
	 * @param fac Facility object to be added to the database
	 */
	public void addNewFacility(Facility fac) {
		Connection con = DBHelper.getConnection();
        PreparedStatement facPst = null;
        PreparedStatement addPst = null;

        try {
        	//Insert the facility object
            String facStm = "INSERT INTO facility(id) VALUES(?)";
            facPst = con.prepareStatement(facStm);
            facPst.setInt(1, fac.getFacilityID());
            facPst.executeUpdate();

        	//Insert the facility_detail object
            String addStm = "INSERT INTO facility_detail(name, facility_id, number_of_rooms, phone) VALUES(?, ?, ?, ?)";
            addPst = con.prepareStatement(addStm);
            addPst.setString(1, fac.getDetailsAboutFacility().getName());
            addPst.setInt(2, fac.getDetailsAboutFacility().getFacilityID());  
            addPst.setInt(3, fac.getDetailsAboutFacility().getNumberOfRooms());
            if (fac.getDetailsAboutFacility().getPhoneNumber() != 0) {
            	addPst.setInt(4, fac.getDetailsAboutFacility().getPhoneNumber());
            } else {
            	addPst.setNull(4, java.sql.Types.INTEGER);
            }
            addPst.executeUpdate();
        } catch (SQLException ex) {

        } finally {

            try {
                if (addPst != null) {
                	addPst.close();
                	facPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("FacilityDAO: Threw a SQLException saving the facility object.");
    	      System.err.println(ex.getMessage());
            }
        }
    }
	
	/***
	 * Adds optional detail information (phone number) about the facility
	 * @param ID facility ID
	 * @param phoneNumber number to be added
	 */
	public void addFacilityDetail(int ID, int phoneNumber) {
		
		try { 		
	    	Connection con = DBHelper.getConnection();
			PreparedStatement facPst = null;
	    	//Get Facility
	        
	    	String updateFacilityDetailQuery = "UPDATE facility_detail SET phone = ? WHERE facility_id = ?";
	    
            facPst = con.prepareStatement(updateFacilityDetailQuery);
            facPst.setInt(1, phoneNumber);
            facPst.setInt(2, ID);
            facPst.executeUpdate();
    
	    	System.out.println("FacilityDAO: *************** Query " + updateFacilityDetailQuery + "\n");
	    	
	    	//close to manage resources
	    	facPst.close();
	    	con.close();
	    	  
	   }	    
	   catch (SQLException se) {
	      System.err.println("FacilityDAO: Threw a SQLException updating the phone number in Facility Detail table.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	   }
		
	}
	
	/***
	 * List the name and ID number of all the facilities
	 * @return list of facilities
	 */
	public List<Facility> listFacilities() {
		
		List<Facility> listOfFac = new ArrayList<Facility>();
		
		try {
			
			Statement st = DBHelper.getConnection().createStatement();
	    	String getAllFacilitiesQuery = "SELECT * FROM facility";

	    	ResultSet facRS = st.executeQuery(getAllFacilitiesQuery);      
	    	System.out.println("FacilityDAO: *************** Query " + getAllFacilitiesQuery + "\n");
	    	
	    	Facility fac1 = new Facility();
		    while ( facRS.next() ) {
		    	fac1.setFacilityID(facRS.getInt("id"));
		    	listOfFac.add(getFacilityInformation(fac1.getFacilityID()));
		    }
		    
		    //close to manage resources
		    facRS.close();
		    st.close();
		    
		    return listOfFac;
	    	
		}
    	catch (SQLException se) {
  	      System.err.println("FacilityDAO: Threw a SQLException retrieving list of facilities.");
  	      System.err.println(se.getMessage());
  	      se.printStackTrace();
  	    }
	    	
		return null;
	    	
	}
	
}
