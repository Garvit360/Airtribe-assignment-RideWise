package org.example.strategy;

import org.example.exception.NoDriverAvailableException;
import org.example.model.Driver;
import org.example.model.Rider;

import java.util.List;

/**
 * STRATEGY PATTERN - NearestDriverStrategy
 * 
 * Finds the driver closest to the rider's location using geographic distance.
 * Uses the Haversine formula implemented in the Location class for accurate
 * distance calculations between GPS coordinates.
 */
public class NearestDriverStrategy implements RideMatchingStrategy {

    @Override
    public Driver findDriver(Rider rider, List<Driver> availableDrivers) {
        // 1. Validate inputs
        if (rider == null) {
            throw new IllegalArgumentException("Rider cannot be null");
        }

        if (rider.getRiderLocation() == null) {
            throw new IllegalArgumentException("Rider location cannot be null");
        }

        if (availableDrivers == null || availableDrivers.isEmpty()) {
            throw new NoDriverAvailableException(rider.getRiderLocation(), 0);
        }

        // 2. Initialize variables to track nearest driver and minimum distance
        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        // 3. Loop through available drivers to find the nearest one
        for (Driver driver : availableDrivers) {
            if (driver == null || driver.getDriverLocation() == null) {
                continue; // Skip invalid drivers
            }

            // Calculate distance between rider and driver locations
            double distance = rider.getRiderLocation().calculateDistanceTo(driver.getDriverLocation());

            // Update nearest driver if this one is closer
            if (distance < minDistance) {
                minDistance = distance;
                nearestDriver = driver;
            }
        }

        // 4. Handle case where no valid driver was found
        if (nearestDriver == null) {
            throw new NoDriverAvailableException(
                    String.format("No valid drivers found at location %s (checked %d drivers)",
                            rider.getRiderLocation().toString(), availableDrivers.size()));
        }

        return nearestDriver;
    }
}
