package com.facility.maintenance;

import java.util.List;

public class Maintenance {
	
	private int totalCost = 0;
	private boolean maintenanceAvailable;
	private List<MaintRequest> listOfMaintRequests;
	
	public Maintenance() {
		setMaintenanceAvailable(true);
	}
	
	public void makeFacilityMaintRequest(int maintID, int cost) {
		MaintRequest request = new MaintRequest(maintID, cost);
		listOfMaintRequests.add(request);
	}
	
	public List<MaintRequest> listMaintRequests() {
		return listOfMaintRequests;
	}
	
	public int calcMaintenanceCostForFacility() {
		return totalCost;
	}
	
	public void setMaintenanceAvailable(boolean status) {
		maintenanceAvailable = status;
	}
	
	public boolean getMaintenanceAvailable() {
		return maintenanceAvailable;
	}
}
