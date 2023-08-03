package com.test.parking.service;

import com.test.parking.constants.VehicleSize;
import com.test.parking.exception.InvalidVehicleNumberException;
import com.test.parking.exception.InvalidVehicleSizeException;
import com.test.parking.exception.ParkingFullException;
import com.test.parking.vo.Slot;
import com.test.parking.vo.Ticket;
import com.test.parking.vo.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    private static ParkingServiceImpl parking;
    private final List<Slot> fourWheelerSlots;

    private ParkingServiceImpl() {
        this.fourWheelerSlots = new ArrayList<>();
    }

    public static ParkingServiceImpl getParking() {
        if (parking == null) {
            parking = new ParkingServiceImpl();
        }
        return parking;
    }

    public boolean initializeParkingSlots(int numberOfFourWheelerParkingSlots) {
        for (int i = 1; i <= numberOfFourWheelerParkingSlots; i++) {
            fourWheelerSlots.add(new Slot(i));
        }

        System.out.printf("Created a four wheeler parking lot with %s slots %n", numberOfFourWheelerParkingSlots);
        return true;
    }


    @Override
    public Ticket park(Vehicle vehicle) throws ParkingFullException, InvalidVehicleSizeException {
        Slot nextAvailableSlot;
        if (vehicle.getVehicleSize().equals(VehicleSize.FOUR_WHEELER)) {
            nextAvailableSlot = getNextAvailableFourWheelerSlot();
        } else {
            throw new InvalidVehicleSizeException("Invalid vehicle size");
        }
        nextAvailableSlot.occupySlot(vehicle);
        System.out.printf("For the car %s, allocated slot number: %d \n", vehicle.getVehicleNumber(), nextAvailableSlot.getSlotNumber());

        return new Ticket(nextAvailableSlot.getSlotNumber(), vehicle.getVehicleNumber(),
                vehicle.getVehicleSize(), new Date());
    }

    private Slot getNextAvailableFourWheelerSlot() throws ParkingFullException {
        for (Slot slot : fourWheelerSlots) {
            if (slot.isEmpty()) {
                return slot;
            }
        }
        throw new ParkingFullException("Parking full. No Empty Slot available");
    }

    @Override
    public int unPark(Ticket ticket, ParkingChargeStrategy parkingCostStrategy) throws InvalidVehicleNumberException, InvalidVehicleSizeException {
        int costByHours = 0;
        Slot slot;
        try {
            if (ticket.getVehicleSize().equals(VehicleSize.FOUR_WHEELER)) {
                slot = getFourWheelerSlotByVehicleNumber(ticket.getVehicleNumber());
            } else {
                throw new InvalidVehicleSizeException("Invalid vehicle size");
            }
            slot.vacateSlot();
            int hours = getHoursParked(ticket.getDate(), new Date());
            costByHours = parkingCostStrategy.getCharge(hours);
            System.out.println(
                    "Vehicle with registration " + ticket.getVehicleNumber() + " at slot number " + slot.getSlotNumber()
                            + " was parked for " + hours + " hours and the total charge is " + costByHours);
        } catch (InvalidVehicleNumberException invalidVehicleNumber) {
            System.out.println(invalidVehicleNumber.getMessage());
            throw invalidVehicleNumber;
        }
        return costByHours;
    }

    private int getHoursParked(Date startDate, Date endDate) {
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        return Math.round(secs / 3600);

    }

    private Slot getFourWheelerSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumberException {
        for (Slot slot : fourWheelerSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        throw new InvalidVehicleNumberException("Two wheeler with registration number " + vehicleNumber + " not found");
    }

}
