package com.facility.view;

import com.facility.base.Facility;
import com.facility.service.UseService;
import com.facility.use.Inspection;

public class UseClient {

	public UseClient() throws Exception {
		
		UseService useService = new UseService();
		System.out.println("\nUseClient: *************** Listing the inspections at a facility *************************");
		Facility fact = new Facility();;
		fact.setFacilityID(7);
		
		System.out.println("\n\tInspections At Facility #" + fact.getFacilityID());
		for (Inspection inspec : useService.listInspections(fact)) {
			System.out.println("\t" + inspec.getInspection_type() +
					" status: " + inspec.getInspection_detail());
		}
		
		
		
		
	}
	
	
}
