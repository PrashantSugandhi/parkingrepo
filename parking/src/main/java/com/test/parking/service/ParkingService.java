package com.test.parking.service;

import com.test.parking.exception.InvalidVehicleNumberException;
import com.test.parking.exception.InvalidVehicleSizeException;
import com.test.parking.exception.ParkingFullException;
import com.test.parking.vo.Ticket;
import com.test.parking.vo.Vehicle;

/**
 * Responsibility: This interface will the behaviour of park and unpark.
 */
public interface ParkingService {
    public Ticket park(Vehicle vehicle) throws ParkingFullException, InvalidVehicleSizeException;

    public int unPark(Ticket ticket, ParkingChargeStrategy parkingCostStrategy) throws InvalidVehicleNumberException, InvalidVehicleSizeException;
}
