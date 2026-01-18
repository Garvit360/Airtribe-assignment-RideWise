package org.example.model;

import org.example.Interface.Searchable;
import org.example.util.IdGenerator;

public class Ride implements Searchable {

    private String rideId;
    private Rider rider;
    private Driver driver;
    private double distance;
    private RideStatus rideStatus;
    private VehicleType vehicleType;

    public Ride() {
    }

    public Ride(Rider rider, Driver driver, double distance, RideStatus rideStatus, VehicleType vehicleType) {
        this.rideId = IdGenerator.generateRideId();
        this.rider = rider;
        this.driver = driver;
        this.distance = distance;
        this.rideStatus = rideStatus;
        this.vehicleType = vehicleType;
    }

    public String getRideId() {
        return rideId;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean matchesSearchCriteria(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        String lowerKeyword = keyword.toLowerCase();
        return (rideId != null && rideId.toLowerCase().contains(lowerKeyword)) ||
                (rider != null && rider.getName() != null && rider.getName().toLowerCase().contains(lowerKeyword)) ||
                (driver != null && driver.getName() != null && driver.getName().toLowerCase().contains(lowerKeyword)) ||
                (rideStatus != null && rideStatus.name().toLowerCase().contains(lowerKeyword)) ||
                (vehicleType != null && vehicleType.name().toLowerCase().contains(lowerKeyword));
    }

    @Override
    public void displaySearchResult() {
        System.out.println("=== Ride Found ===");
        display();
    }

    public void display() {
        System.out.println("Ride ID: " + rideId);
        if (rider != null) {
            System.out.println("Rider: " + rider.getName() + " (ID: " + rider.getRiderId() + ")");
        }
        if (driver != null) {
            System.out.println("Driver: " + driver.getName() + " (ID: " + driver.getDriverId() + ")");
        }
        System.out.println("Distance: " + String.format("%.2f", distance) + " km");
        System.out.println("Status: " + (rideStatus != null ? rideStatus.name() : "N/A"));
        System.out.println("Vehicle Type: " + (vehicleType != null ? vehicleType.name() : "N/A"));
        System.out.println("-------------------");
    }
}
