package com.test.parking.vo;

import com.test.parking.constants.VehicleSize;
import lombok.Data;

import java.util.Date;

/**
 * Responsibility: Once the vehicle has been parked, the owner will be provided with the Ticket. It will have the
 * slotNumber, vehicle number, time at which the vehicle has been parked and the vehicle size.
 */
@Data
public class Ticket {
    private int slotNumber;
    private String vehicleNumber;
    private Date date;
    private VehicleSize vehicleSize;

    public Ticket(int slotNumber, String vehicleNumber, VehicleSize vehicleSize, Date date) {
        this.slotNumber = slotNumber;
        this.vehicleNumber = vehicleNumber;
        this.date = date;
        this.vehicleSize = vehicleSize;
    }
}
