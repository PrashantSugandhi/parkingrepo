## DESIGN

**Design Patterns and Principles**

* Singleton Design Pattern
* Strategy Design Pattern
* SOLID Design Principles

## ADR

**Requirements**

* Parking will have only cars i.e. four wheeler
* Parking charges will be 2 pound per hour, rounding up to the nearest hour

**Design code**

* `Enum VehicleSize` - This enum will have vehicle size as FOUR_WHEELER (in future we can add other vehicles too)
* `Class Vehicle` - The Vehicle to be parked. It has the vehicle number and the type of vehicle i.e. four-wheeler.
* `Class Slot` - This class represents the space in the parking lot which will be used to park the vehicle. A Parking
  lot will have a finite number of parking slots. Max slots are 100.
* `Class Ticket` — Once the vehicle has been parked, the owner will be provided with the Ticket. It will have the
  slotNumber, vehicle number, time at which the vehicle has been parked and the vehicle size.
* `Interface Parking` - This interface will the behaviour of park and unpark. Its implementation will be singleton class
  so that there is only one parking defined.
* `Interface ParkingStrategy`- It will calculate the charges of car based on hours spent. Used strategy design pattern
  so that in future we can implement different strategy for different vehicles with specific charges.