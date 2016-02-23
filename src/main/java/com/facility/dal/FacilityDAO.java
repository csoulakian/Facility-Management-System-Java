package com.facility.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.facility.base.*;
import com.facility.manager.*;

public class FacilityDAO {

	public FacilityDAO() {}
	
	
	public void removeFacility(int ID) {
		
	    try { 		
	    	Connection con = DBHelper.getConnection();
			 PreparedStatement facPst = null;
	    	//Get Facility
	        
	    	String removeFacilityQuery = "delete from facility where id = '" + ID + "'";
	    
            facPst = con.prepareStatement(removeFacilityQuery);
           // facPst.setInt(1, fac.getFacilityID());
            facPst.executeUpdate();
    
	    	System.out.println("FacilityDAO: *************** Query " + removeFacilityQuery);
	    
	      //close to manage resources
	      //facRS.close();
	      	    		  
	   }	    
	   catch (SQLException se) {
	      System.err.println("FacilityDAO: Threw a SQLException removing the Facility object from Facility table.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	 	 
	    try { 		
	    	//Get Facility
	    	Connection con = DBHelper.getConnection();
			 PreparedStatement facPst = null;
	        
	    	String removeFacilityDetailQuery = "delete from facility_detail where facility_id = '" + ID + "'";
	    
            facPst = con.prepareStatement(removeFacilityDetailQuery);
           // facPst.setInt(1, fac.getFacilityID());
            facPst.executeUpdate();
    
	    	System.out.println("FacilityDAO: *************** Query " + removeFacilityDetailQuery);
	    
	      //close to manage resources
	      //facRS.close();
	      	    		  
	   }	    
	   catch (SQLException se) {
	      System.err.println("FacilityDAO: Threw a SQLException removing the Facility Detail from Facility Detail table.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	  }
	
	public Facility getFacilityInformation(int ID) {
	 	 
	    try { 		
	    	//Get Facility
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectFacilityQuery = "SELECT id from facility where id = '" + ID + "'";

	    	ResultSet facRS = st.executeQuery(selectFacilityQuery);      
	    	System.out.println("FacilityDAO: *************** Query " + selectFacilityQuery);
	    	
	      //Get Customer
    	  Facility fac1 = new Facility();
	      while ( facRS.next() ) {
	    	  fac1.setFacilityID(facRS.getInt("id"));
	    	
	      }
	      //close to manage resources
	      facRS.close();
	      	    		  
	      //Get Address
	      String selectDetailQuery = "SELECT name, facility_id,number_of_rooms,phone FROM facility_detail WHERE facility_id = '" + ID + "'";
	      ResultSet detRS = st.executeQuery(selectDetailQuery);
    	  FacilityDetail detail = new FacilityDetail();
    	  
    	  System.out.println("FacilityDAO: *************** Query " + selectDetailQuery);
    	  
	      while ( detRS.next() ) {
	    	  detail.setName(detRS.getString("name"));
	    	  detail.setFacilityID(detRS.getInt("facility_id"));
	    	  detail.setNumberOfRooms(detRS.getInt("number_of_rooms"));
	    	  detail.setPhoneNumber(detRS.getInt("phone"));
	      }
	      
	      fac1.setDetailsAboutFacility(detail);
	      //close to manage resources
	      detRS.close();
	      st.close();
	      
	      return fac1;
	    }	    
	    catch (SQLException se) {
	      System.err.println("FacilityDAO: Threw a SQLException retrieving the Facility object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }
	
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

        	//Insert the customer address object
            String addStm = "INSERT INTO facility_detail(name, facility_id, number_of_rooms, phone) VALUES(?, ?, ?, ?)";
            addPst = con.prepareStatement(addStm);
            addPst.setString(1, fac.getDetailsAboutFacility().getName());
            addPst.setInt(2, fac.getDetailsAboutFacility().getFacilityID());  
            addPst.setInt(3, fac.getDetailsAboutFacility().getNumberOfRooms());       
            addPst.setInt(4, fac.getDetailsAboutFacility().getPhoneNumber());
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
	
}
