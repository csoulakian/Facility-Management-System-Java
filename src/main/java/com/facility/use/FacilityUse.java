package com.facility.use;

import java.time.LocalDate;

import com.facility.base.Facility;

public class FacilityUse extends Facility {
	
	private int roomNumber;
	private LocalDate startDate;
	private LocalDate endDate;

	public FacilityUse() {}
	
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
