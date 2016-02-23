package com.facility.maintenance;

public class MaintRequest {
	
	private int maintID;
	private int cost;
	
	public MaintRequest(int maintenanceID, int maintenanceCost) {
		setMaintID(maintenanceID);
		setCost(maintenanceCost);
	}

	public void setMaintID(int maintID) {
		this.maintID = maintID;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getMaintID() {
		return maintID;
	}
	
	public int getCost() {
		return cost;
	}

	
}
