package com.test.parking.service;

public class FourWheelerParkingChargeStrategy implements ParkingChargeStrategy {
    @Override
    public int getCharge(int parkHours) {
        if (parkHours < 1) {
            return 2;
        }
        return parkHours * 2;
    }
}
