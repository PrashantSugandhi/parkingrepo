package com.test.parking.service;

/**
 * Responsibility: To calculate the parking charges
 * Note : We are using Strategy Design pattern keeping in mind that in future we might introduce more vehicle size with different parking charges
 */
public interface ParkingChargeStrategy {
    int getCharge(int parkHours);
}
