package org.example.strategy;

import org.example.exception.NoDriverAvailableException;
import org.example.model.Driver;
import org.example.model.Rider;

import java.util.List;

/**
 * STRATEGY PATTERN - LeastActiveDriverStrategy
 * 
 * Finds the driver with the fewest completed rides to distribute work evenly.
 * Uses the completedRidesCount field in the Driver model to track activity.
 * In case of ties (same ride count), returns the first driver found for
 * simplicity.
 * 
 * DESIGN DECISIONS:
 * - "Least active" = fewest completed rides
 * - Driver maintains completedRidesCount field for performance
 * - Tie-breaking: return first driver found (could be enhanced with distance)
 */
public class LeastActiveDriverStrategy implements RideMatchingStrategy {

    @Override
    public Driver findDriver(Rider rider, List<Driver> availableDrivers) {
        
        if (rider == null) {
            throw new IllegalArgumentException("Rider cannot be null");
        }

        if (availableDrivers == null || availableDrivers.isEmpty()) {
            throw new NoDriverAvailableException(
                    rider.getRiderLocation() != null ? rider.getRiderLocation() : null, 0);
        }

        Driver leastActiveDriver = null;
        int minRideCount = Integer.MAX_VALUE;

        // find the least active driver
        for (Driver driver : availableDrivers) {
            if (driver == null) {
                continue;
            }

            int driverRideCount = driver.getCompletedRidesCount();

            // Update least active driver if this one has fewer rides
            if (driverRideCount < minRideCount) {
                minRideCount = driverRideCount;
                leastActiveDriver = driver;
            }
            // In case of tie, keep the first one found
        }

        // 4. Handle case where no valid driver was found
        if (leastActiveDriver == null) {
            throw new NoDriverAvailableException(
                    String.format("No valid drivers found (checked %d drivers)", availableDrivers.size()));
        }

        return leastActiveDriver;
    }
}
