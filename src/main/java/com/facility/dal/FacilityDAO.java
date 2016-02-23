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
