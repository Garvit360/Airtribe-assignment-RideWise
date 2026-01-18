package org.example.service;

import org.example.exception.NoDriverAvailableException;
import org.example.exception.RideNotFoundException;
import org.example.model.*;
import org.example.strategy.FareCalculationStrategy;
import org.example.strategy.RideMatchingStrategy;
import org.example.util.DataStore;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RideService {

    private DataStore<Ride> rideStore;
    private DriverService driverService;
    private RiderService riderService;
    private FareCalculationStrategy fareCalculationStrategy;
    private RideMatchingStrategy rideMatchingStrategy;

    public RideService(DriverService driverService, RiderService riderService,
            RideMatchingStrategy rideMatchingStrategy,
            FareCalculationStrategy fareCalculationStrategy) {
        this.rideStore = new DataStore<>();
        this.driverService = driverService;
        this.riderService = riderService;
        this.rideMatchingStrategy = rideMatchingStrategy;
        this.fareCalculationStrategy = fareCalculationStrategy;
    }

    public Ride requestRide(String riderId, Location pickup, Location dropOff, VehicleType vehicleType) {
        Rider rider = riderService.getRider(riderId);

        // Update rider location to pickup location
        rider.setRiderLocation(pickup);
        riderService.updateRider(rider);

        // Get only available drivers
        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            throw new NoDriverAvailableException("No drivers available");
        }

        // Use strategy to find the best driver
        Driver driver = rideMatchingStrategy.findDriver(rider, availableDrivers);

        // Calculate distance
        double distance = pickup.calculateDistanceTo(dropOff);

        // Create ride with REQUESTED status
        Ride ride = new Ride(rider, driver, distance, RideStatus.REQUESTED, vehicleType);

        // Update ride status to ASSIGNED
        ride.setRideStatus(RideStatus.ASSIGNED);

        // Mark driver as unavailable
        driver.setAvailable(false);
        driverService.updateDriver(driver);

        // Store the ride
        rideStore.add(ride.getRideId(), ride);
        rideStore.update(ride.getRideId(), ride); // Update to save ASSIGNED status

        return ride;
    }

    public FareReceipt completeRide(String rideId) throws RideNotFoundException {
        Ride ride = rideStore.findById(rideId);

        if (ride == null) {
            throw new RideNotFoundException("Ride not found");
        }

        // The ride status is expected to be ASSIGNED before completion, not just any
        // status
        if (!ride.getRideStatus().equals(RideStatus.ASSIGNED)) {
            throw new IllegalStateException("Ride must be in ASSIGNED status to complete");
        }

        double fare = fareCalculationStrategy.calculateFare(ride);

        ride.setRideStatus(RideStatus.COMPLETED);
        rideStore.update(ride.getRideId(), ride);

        Driver driver = ride.getDriver();
        driver.setAvailable(true);
        driver.incrementCompletedRides();
        driverService.updateDriver(driver);

        FareReceipt receipt = new FareReceipt(rideId, fare, Instant.now());

        return receipt;
    }

    public void cancelRide(String rideId) throws RideNotFoundException {
        Ride ride = rideStore.findById(rideId);
        if (ride == null) {
            throw new RideNotFoundException("Ride not found");
        }
        if (ride.getRideStatus().equals(RideStatus.ASSIGNED)) {
            ride.setRideStatus(RideStatus.CANCELLED);
            rideStore.update(ride.getRideId(), ride);

            // Mark driver as available again
            Driver driver = ride.getDriver();
            if (driver != null) {
                driver.setAvailable(true);
                driverService.updateDriver(driver);
            }
        }
    }

    public Ride getRide(String rideId) throws RideNotFoundException {
        Ride ride = rideStore.findById(rideId);
        if (ride == null) {
            throw new RideNotFoundException("Ride not found");
        }
        return ride;
    }

    public List<Ride> getAllRides() {
        return new ArrayList<>(rideStore.getAll());
    }
}
