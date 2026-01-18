package org.example.model;

import org.example.Interface.Searchable;
import org.example.util.IdGenerator;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class FareReceipt implements Searchable {

    private String receiptId;
    private String rideId;
    private double amount;
    private Instant generatedAt;

    public FareReceipt() {
    }

    public FareReceipt(String rideId, double amount, Instant generatedAt) {
        this.receiptId = IdGenerator.generateReceiptId();
        this.rideId = rideId;
        this.amount = amount;
        this.generatedAt = generatedAt;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Instant getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Instant generatedAt) {
        this.generatedAt = generatedAt;
    }

    @Override
    public boolean matchesSearchCriteria(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        String lowerKeyword = keyword.toLowerCase();
        return (receiptId != null && receiptId.toLowerCase().contains(lowerKeyword)) ||
                (rideId != null && rideId.toLowerCase().contains(lowerKeyword)) ||
                String.valueOf(amount).contains(keyword);
    }

    @Override
    public void displaySearchResult() {
        System.out.println("=== Receipt Found ===");
        display();
    }

    public void display() {
        System.out.println("Receipt ID: " + receiptId);
        System.out.println("Ride ID: " + rideId);
        System.out.println("Amount: ₹" + String.format("%.2f", amount));
        if (generatedAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("Generated At: " + formatter.format(generatedAt));
        }
        System.out.println("-------------------");
    }
}
