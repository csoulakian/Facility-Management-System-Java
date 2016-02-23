package com.facility.use;

import java.util.Date;

import com.facility.base.Facility;

public class FacilityUse {
	
	private int actualUsage;
	private double usageRate;

	public FacilityUse() {}
	
	public int getActualUsage() {
		return actualUsage;
	}

	public void setActualUsage(int actualUsage) {
		this.actualUsage = actualUsage;
	}

	public double getUsageRate() {
		return usageRate;
	}

	public void setUsageRate(double usageRate) {
		this.usageRate = usageRate;
	}
}
