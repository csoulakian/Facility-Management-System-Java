package com.facility.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.facility.base.Facility;
import com.facility.maintenance.Maintenance;

public class MaintenanceDAO {

	public MaintenanceDAO() {}
	
	/***
	 * Creates a new maintenance request and inserts it into the maint_request table.
	 * @param fac Facility where the maintenance request is made
	 * @param maintenanceDetails description of the maintenance requested
	 * @param cost of maintenance
	 * @return the Maintenance object that was requested
	 */
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
	
	/***
	 * Schedules a maintenance request by first adding it to the maintenance table and 
	 * then removing it from the maint_request table.
	 * @param maintRequest the request to be scheduled
	 */
	public void scheduleMaintenance(Maintenance maintRequest) {
		
		 try { 		
		    				
			Statement st = DBHelper.getConnection().createStatement();
	    	String scheduleMaintenanceAddQuery = "INSERT INTO maintenance (facility_id, details, cost) VALUES (" + 
	    			maintRequest.getFacilityID() + ", '" + maintRequest.getDetails() + 
	    			"', " + maintRequest.getCost() + ")";
	    	st.execute(scheduleMaintenanceAddQuery);      
	    	System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceAddQuery + "\n");
		    
		    //close to manage resources
		    st.close();
	    	  
	    }	    
	    catch (SQLException se) {
		   System.err.println("MaintenanceDAO: Threw a SQLException adding a maintenance "
		   		+ "request to maintenance table.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
	    }
		 
		try { 		
				
			Statement st = DBHelper.getConnection().createStatement();
	    	String scheduleMaintenanceRemoveQuery = "DELETE FROM maint_request WHERE facility_id = " + 
	    			maintRequest.getFacilityID() + " AND details = '" + maintRequest.getDetails() + 
	    			"' AND cost = " + maintRequest.getCost();
	    	st.execute(scheduleMaintenanceRemoveQuery);      
	    	System.out.println("MaintenanceDAO: *************** Query " + scheduleMaintenanceRemoveQuery + "\n");
		    
		    //close to manage resources
		    st.close();
		    	  
		    }	    
	    catch (SQLException se) {
		   System.err.println("MaintenanceDAO: Threw a SQLException removing a "
		   		+ "maintenance request from maint_request table.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
	    }
		
	}
	
	/***
	 * Calculates the sum of completed maintenance at a particular facility from the maintenance table.
	 * @param fac Facility to calculate maintenance cost
	 * @return total cost of completed maintenance
	 */
	public int calcMaintenanceCostForFacility(Facility fac) {
		
		try { 		

			int totalCost = 0;
			
			Statement st = DBHelper.getConnection().createStatement();
	    	String calcMaintenanceCostQuery = "SELECT SUM(cost) FROM maintenance "
	    			+ "WHERE facility_id = " + fac.getFacilityID();
	    	ResultSet maintRS = st.executeQuery(calcMaintenanceCostQuery);
	    	
	    	while ( maintRS.next() ) {
	    		totalCost = maintRS.getInt(1);
	    	}
	    	System.out.println("MaintenanceDAO: *************** Query " + calcMaintenanceCostQuery + "\n");
		    
		    //close to manage resources
		    maintRS.close();
	    	st.close();
		    
		    return totalCost;
	    	  
	    }	    
	    catch (SQLException se) {
		   System.err.println("MaintenanceDAO: Threw a SQLException calculating total "
		   		+ "maintenance cost from maintenance table.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
	    }
		
		return 0;
	}
	
	/***
	 * Lists Maintenance objects from the maint_request table that have yet to be 
	 * scheduled/completed at a particular facility.
	 * @param fac Facility to list maintenance requests for
	 * @return a list of Maintenance objects containing maintenance requests
	 */
	public List<Maintenance> listMaintRequests(Facility fac) {
		
		List<Maintenance> listOfMaintRequests = new ArrayList<Maintenance>();
		
		try { 		
	    	
    		Statement st = DBHelper.getConnection().createStatement();
	    	String listMaintRequestsQuery = "SELECT * FROM maint_request WHERE facility_id = '" + 
	    			fac.getFacilityID() + "' ORDER BY cost";

	    	ResultSet maintRS = st.executeQuery(listMaintRequestsQuery);      
	    	System.out.println("UseDAO: *************** Query " + listMaintRequestsQuery + "\n");
	    	
		    while ( maintRS.next() ) {
		    	Maintenance maintenanceRequest = new Maintenance();
		    	maintenanceRequest.setDetails(maintRS.getString("details"));
		    	maintenanceRequest.setCost(maintRS.getInt("cost"));
		    	maintenanceRequest.setFacilityID(fac.getFacilityID());
		    	listOfMaintRequests.add(maintenanceRequest);
		    }
		    
		    //close to manage resources
		    maintRS.close();
		    st.close();
	    	  
	   }	    
	   catch (SQLException se) {
		   System.err.println("UseDAO: Threw a SQLException retreiving list of maintenance "
		   		+ "requests from maint_request table.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
	   }
		
		return listOfMaintRequests;
		
	}
	
	/***
	 * Lists Maintenance objects from the maintenance table that have already been
	 * completed at a particular facility.
	 * @param fac Facility to list maintenance for
	 * @return a list of Maintenance objects containing completed maintenance
	 */
	public List<Maintenance> listMaintenance(Facility fac) {
		
		List<Maintenance> listOfCompletedMaintenance = new ArrayList<Maintenance>();
		
		try { 		
	    	
    		Statement st = DBHelper.getConnection().createStatement();
	    	String listMaintenanceQuery = "SELECT * FROM maintenance WHERE facility_id = '" + 
	    			fac.getFacilityID() + "' ORDER BY cost";

	    	ResultSet maintRS = st.executeQuery(listMaintenanceQuery);      
	    	System.out.println("UseDAO: *************** Query " + listMaintenanceQuery + "\n");
	    	
		    while ( maintRS.next() ) {
		    	Maintenance maintenance = new Maintenance();
		    	maintenance.setDetails(maintRS.getString("details"));
		    	maintenance.setCost(maintRS.getInt("cost"));
		    	maintenance.setFacilityID(fac.getFacilityID());
		    	listOfCompletedMaintenance.add(maintenance);
		    }
		    
		    //close to manage resources
		    maintRS.close();
		    st.close();
	    	  
		}	    
		catch (SQLException se) {
		   System.err.println("UseDAO: Threw a SQLException retreiving list of maintenance "
		   		+ "from maintenanace table.");
		   System.err.println(se.getMessage());
		   se.printStackTrace();
		}
		
		return listOfCompletedMaintenance;
		
	}
	
}
