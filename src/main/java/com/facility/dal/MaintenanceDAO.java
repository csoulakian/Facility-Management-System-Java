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
import com.facility.maintenance.Maintenance;

public class MaintenanceDAO {

	public MaintenanceDAO() {}
	
	public Maintenance makeFacilityMaintRequest(Facility fac, String maintenanceDetails, int cost) {
		
		 try { 		
	    	
			Maintenance maint = new Maintenance();
			maint.setDetails(maintenanceDetails);
			maint.setCost(cost);
			maint.setFacilityID(fac.getFacilityID());
			
    		Statement st = DBHelper.getConnection().createStatement();
	    	String makeMaintRequestQuery = "INSERT INTO maint_request (facility_id, details, cost) VALUES (" + 
	    			fac.getFacilityID() + ", '" + maintenanceDetails + "', " + cost + ")";
	    	st.execute(makeMaintRequestQuery);      
	    	System.out.println("MaintenanceDAO: *************** Query " + makeMaintRequestQuery + "\n");
		    
		    //close to manage resources
		    st.close();
		    
		    return maint;
	    	  
	    }	    
	    catch (SQLException se) {
		   System.err.println("MaintenanceDAO: Threw a SQLException making a maintenance request.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
	    }
		
		return null;
		
	}
	
	public void scheduleMaintenance(Maintenance maint) {
		
		 try { 		
		    				
			Statement st = DBHelper.getConnection().createStatement();
	    	String scheduleMaintenanceAddQuery = "INSERT INTO maintenance (facility_id, details, cost) VALUES (" + 
	    			maint.getFacilityID() + ", '" + maint.getDetails() + "', " + maint.getCost() + ")";
	    	st.execute(scheduleMaintenanceAddQuery);      
	    	System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceAddQuery + "\n");
		    
		    //close to manage resources
		    st.close();
	    	  
	    }	    
	    catch (SQLException se) {
		   System.err.println("MaintenanceDAO: Threw a SQLException adding a maintenance request to maintenance table.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
	    }
		 
		try { 		
				
			Statement st = DBHelper.getConnection().createStatement();
	    	String scheduleMaintenanceRemoveQuery = "DELETE FROM maint_request WHERE facility_id = " + maint.getFacilityID() + " AND details = '" + maint.getDetails() + "' AND cost = " + maint.getCost();
	    	st.execute(scheduleMaintenanceRemoveQuery);      
	    	System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceRemoveQuery + "\n");
		    
		    //close to manage resources
		    st.close();
		    	  
		    }	    
	    catch (SQLException se) {
		   System.err.println("MaintenanceDAO: Threw a SQLException removing a maintenance request from maint_request table.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
	    }
		
	}
	
}
