package org.example.strategy;

import org.example.model.Ride;
import org.example.model.VehicleType;

public class DefaultFareStrategy implements FareCalculationStrategy {

    // Base rates per kilometer for each vehicle type
    private static final double BIKE_BASE_RATE = 2.0;
    private static final double AUTO_BASE_RATE = 3.5;
    private static final double CAR_BASE_RATE = 5.0;
    private static final double BUS_BASE_RATE = 1.5;

    @Override
    public double calculateFare(Ride ride) {
        if (ride == null) {
            throw new IllegalArgumentException("Ride cannot be null");
        }

        if (ride.getDistance() <= 0) {
            throw new IllegalArgumentException("Ride distance must be positive");
        }

        if (ride.getVehicleType() == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }

        double baseRate = getBaseRateForVehicleType(ride.getVehicleType());
        double distance = ride.getDistance();

        return Math.round((baseRate * distance) * 100.0) / 100.0; // Round to 2 decimal places
    }

    private double getBaseRateForVehicleType(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
                return BIKE_BASE_RATE;
            case AUTO:
                return AUTO_BASE_RATE;
            case CAR:
                return CAR_BASE_RATE;
            case BUS:
                return BUS_BASE_RATE;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}
