package org.example.model;

import org.example.Interface.Searchable;
import org.example.util.IdGenerator;

public class Driver implements Searchable {

    private String driverId;
    private String name;
    private String email;
    private String phone;
    private Location driverLocation;
    private boolean isAvailable;
    private int completedRidesCount;

    public Driver() {
        this.completedRidesCount = 0;
    }

    public Driver(String name, String email, String phone, Location driverLocation, boolean isAvailable) {
        this.driverId = IdGenerator.generateDriverId();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.driverLocation = driverLocation;
        this.isAvailable = isAvailable;
        this.completedRidesCount = 0;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(Location driverLocation) {
        this.driverLocation = driverLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getCompletedRidesCount() {
        return completedRidesCount;
    }

    public void setCompletedRidesCount(int completedRidesCount) {
        this.completedRidesCount = completedRidesCount;
    }

    /**
     * Increments the completed rides count by 1
     */
    public void incrementCompletedRides() {
        this.completedRidesCount++;
    }

    @Override
    public boolean matchesSearchCriteria(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        String lowerKeyword = keyword.toLowerCase();
        return (name != null && name.toLowerCase().contains(lowerKeyword)) ||
                (email != null && email.toLowerCase().contains(lowerKeyword)) ||
                (phone != null && phone.contains(keyword)) ||
                (driverId != null && driverId.toLowerCase().contains(lowerKeyword));
    }

    @Override
    public void displaySearchResult() {
        System.out.println("=== Driver Found ===");
        display();
    }

    public void display() {
        System.out.println("Driver ID: " + driverId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        if (driverLocation != null) {
            System.out
                    .println("Location: (" + driverLocation.getLatitude() + ", " + driverLocation.getLongitude() + ")");
        }
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Completed Rides: " + completedRidesCount);
        System.out.println("-------------------");
    }
}
