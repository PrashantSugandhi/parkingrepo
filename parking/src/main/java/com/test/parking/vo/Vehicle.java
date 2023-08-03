package com.test.parking.vo;

import com.test.parking.constants.VehicleSize;
import lombok.Data;

/**
 * Responsibility: The Vehicle to be parked. It has the vehicle number and the type of vehicle i.e. four-wheeler.
 */
@Data
public class Vehicle {
    private String vehicleNumber;
    private VehicleSize vehicleSize;

    public Vehicle(String vehicleNumber, VehicleSize vehicleSize) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleSize = vehicleSize;
    }
}
