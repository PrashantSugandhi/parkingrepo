package com.test.parking.vo;

import lombok.Data;

/**
 * Responsibility: This class represents the space in the parking lot which will be used to park the vehicle. A Parking lot
 * will have a finite number of parking slots. Max slots are 100.
 */

@Data
public class Slot {
    private int slotNumber;
    private boolean isEmpty;
    private Vehicle parkVehicle;

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
        isEmpty = true;
    }

    public void vacateSlot() {
        parkVehicle = null;
        this.isEmpty = true;
    }

    public void occupySlot(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
        this.isEmpty = false;
    }
}
