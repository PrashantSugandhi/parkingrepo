package com.test.parking.service;

import com.test.parking.constants.VehicleSize;
import com.test.parking.exception.InvalidVehicleNumberException;
import com.test.parking.exception.InvalidVehicleSizeException;
import com.test.parking.exception.ParkingFullException;
import com.test.parking.vo.Ticket;
import com.test.parking.vo.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingServiceImplTest {

    static ParkingServiceImpl parking;

    @BeforeAll
    static void beforeAll() {
        parking = ParkingServiceImpl.getParking();
        parking.initializeParkingSlots(100);
    }

    @Test
    @DisplayName("Car parking testing")
    void park() throws ParkingFullException, InvalidVehicleSizeException, InvalidVehicleNumberException {
        Vehicle vehicle_mh12AB1234 = new Vehicle("MH12AB1234", VehicleSize.FOUR_WHEELER);
        Ticket ticket = parking.park(vehicle_mh12AB1234);
        Assertions.assertEquals("MH12AB1234", ticket.getVehicleNumber());
        Assertions.assertEquals(VehicleSize.FOUR_WHEELER, ticket.getVehicleSize());
        Assertions.assertEquals(1, ticket.getSlotNumber());

        // emptying the slot, so that other it does not interfere with other test cases
        parking.unPark(ticket, new FourWheelerParkingChargeStrategy());
    }

    @Test
    @DisplayName("Car un-parking testing")
    void unPark() throws ParkingFullException, InvalidVehicleSizeException, InvalidVehicleNumberException {
        Vehicle vehicle_mh12AB6789 = new Vehicle("MH12AB6789", VehicleSize.FOUR_WHEELER);
        Ticket ticket = parking.park(vehicle_mh12AB6789);

        int cost = parking.unPark(ticket, new FourWheelerParkingChargeStrategy());

        Assertions.assertEquals(2, cost);
    }

    @Test
    @DisplayName("Multiple Car parking test")
    void multipleCarPark() throws ParkingFullException, InvalidVehicleSizeException, InvalidVehicleNumberException {
        Vehicle vehicle_mh12AB1234 = new Vehicle("MH12AB1234", VehicleSize.FOUR_WHEELER);
        Ticket ticket = parking.park(vehicle_mh12AB1234);
        Vehicle vehicle_mh12AB6789 = new Vehicle("MH12AB6789", VehicleSize.FOUR_WHEELER);
        Ticket ticket2 = parking.park(vehicle_mh12AB6789);

        Assertions.assertEquals("MH12AB1234", ticket.getVehicleNumber());
        Assertions.assertEquals(VehicleSize.FOUR_WHEELER, ticket.getVehicleSize());
        Assertions.assertEquals(1, ticket.getSlotNumber());
        Assertions.assertEquals(2, ticket2.getSlotNumber());

        // emptying the slot, so that other it does not interfere with other test cases
        parking.unPark(ticket, new FourWheelerParkingChargeStrategy());
        parking.unPark(ticket2, new FourWheelerParkingChargeStrategy());
    }
}