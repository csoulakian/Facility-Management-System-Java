package com.facility.maintenance;

import com.facility.base.Facility;

public class Maintenance extends Facility {
	
	private String details;
	private int cost;
	
	public Maintenance() {}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
