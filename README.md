# Facility-Management-System-Java
COMP 373/474 - Objects/Frameworks/Patterns - Spring 2016 - Project 1 using PostgreSQL on Heroku

###Project 1: Facility Management System Design and Implementation

**Project Description:**

In this project, we will start providing an object domain model for Facility Management System by
addressing the design and implementation using OOP. The aim is to have a domain model that is
designed using OOP that will be used to study Object relationship and Dependency.

Based on the specification provided for a Facility Management System:

- Identify the main entitles of the problem domain.
- Identify their attributes.
- Identify their relationships.
- Identify their roles and responsibilities.
- Discover other objects that facilitate implementing responsibilities.
- Make available all client interfaces/services specified in the specification document (Facility, Facility Use, and Facility Maintenance Client Interfaces).
- Relational tables to persist the object model you created using any of relational DB of
your choice.
- Persistence layer code using JDBC. 



###Facility Management System Design

**System Name:** Facility Management System

**System Description:** Facilities are the building units (buildings and rooms inside) that a company
uses to support the activities of the business. This system will support the management of buildings,
their constant use, and maintenance support when it is needed. The following are the three main
functionalities of this system:

1. Facility and its description - this will cover the functionalities such as adding a new facility to
be managed; removing a facility from being managed; getting general information such as
the number and capacity of facilities and their current statuses.
2. Facility use – this covers the functionalities of managing the facility such as reserving a
facility; the cost of using and maintaining it; assigning and de-assigning a facility for use.
3. Facility maintenance – this functionality covers the maintenance of a facility such as
scheduling a facility for maintenance; checking maintenance status; listing maintenance
requests; calculating down time of a facility and many more functionalities.

**Client Interfaces:**

- Facility:
  - public object listFacilities ()
  - public object getFacilityInformation()
  - public object requestAvailableCapacity()
  - public object addNewFacility()
  - public void addFacilityDetail()
  - public object removeFacility()
- Facility Use:
  - public object isInUseDuringInterval()
  - public object assignFacilityToUse()
  - public object vacateFacility()
  - public object listInspections()
  - public object listActualUsage()
  - public object calcUsageRate()
- Facility Maintenance: 
  - public object makeFacilityMaintRequest()
  - public object scheduleMaintenance()
  - public object calcMaintenanceCostForFacility()
  - public object calcProblemRateForFacility()
  - public object calcDownTimeForFacility()
  - public object listMaintRequests()
  - public object listMaintenance()
  - public object listFacilityProblems() 
