package org.example.strategy;

import org.example.model.Ride;
import org.example.model.VehicleType;
import java.time.LocalTime;

/**
 * PEAK HOUR FARE CALCULATION STRATEGY
 * 
 * Extends the default fare calculation with peak hour multipliers:
 * - Peak hours: 7:00-9:00 AM and 5:00-7:00 PM
 * - Peak multiplier: 1.5x the base fare
 * - Non-peak hours: Standard rate
 */
public class PeakHourFareStrategy implements FareCalculationStrategy {

    // Base rates per kilometer for each vehicle type (same as DefaultFareStrategy)
    private static final double BIKE_BASE_RATE = 2.0;
    private static final double AUTO_BASE_RATE = 3.5;
    private static final double CAR_BASE_RATE = 5.0;
    private static final double BUS_BASE_RATE = 1.5;

    // Peak hour multiplier
    private static final double PEAK_HOUR_MULTIPLIER = 1.5;

    // Peak hour time ranges
    private static final LocalTime MORNING_PEAK_START = LocalTime.of(7, 0); // 7:00 AM
    private static final LocalTime MORNING_PEAK_END = LocalTime.of(9, 0); // 9:00 AM
    private static final LocalTime EVENING_PEAK_START = LocalTime.of(17, 0); // 5:00 PM
    private static final LocalTime EVENING_PEAK_END = LocalTime.of(19, 0); // 7:00 PM

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
        double baseFare = baseRate * distance;

        // Apply peak hour multiplier if current time is during peak hours
        if (isPeakHour()) {
            baseFare *= PEAK_HOUR_MULTIPLIER;
        }

        return Math.round(baseFare * 100.0) / 100.0; // Round to 2 decimal places
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

    private boolean isPeakHour() {
        LocalTime now = LocalTime.now();

        // Check morning peak (7:00 AM - 9:00 AM)
        boolean morningPeak = (now.isAfter(MORNING_PEAK_START) || now.equals(MORNING_PEAK_START))
                && now.isBefore(MORNING_PEAK_END);

        // Check evening peak (5:00 PM - 7:00 PM)
        boolean eveningPeak = (now.isAfter(EVENING_PEAK_START) || now.equals(EVENING_PEAK_START))
                && now.isBefore(EVENING_PEAK_END);

        return morningPeak || eveningPeak;
    }
}
