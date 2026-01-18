package org.example.model;

import org.example.Interface.Searchable;
import org.example.util.IdGenerator;

public class Rider implements Searchable {

    private String riderId;
    private String name;
    private String email;
    private String phone;
    private Location riderLocation;

    public Rider() {
    }

    public Rider(String name, String email, String phone, Location riderLocation) {
        this.riderId = IdGenerator.generateRiderId();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.riderLocation = riderLocation;
    }

    public String getRiderId() {
        return riderId;
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

    public Location getRiderLocation() {
        return riderLocation;
    }

    public void setRiderLocation(Location riderLocation) {
        this.riderLocation = riderLocation;
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
                (riderId != null && riderId.toLowerCase().contains(lowerKeyword));
    }

    @Override
    public void displaySearchResult() {
        System.out.println("=== Rider Found ===");
        display();
    }

    public void display() {
        System.out.println("Rider ID: " + riderId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        if (riderLocation != null) {
            System.out.println("Location: (" + riderLocation.getLatitude() + ", " + riderLocation.getLongitude() + ")");
        }
        System.out.println("-------------------");
    }
}
