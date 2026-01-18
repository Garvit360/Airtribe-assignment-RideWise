package org.example;

import org.example.exception.*;
import org.example.model.*;
import org.example.service.DriverService;
import org.example.service.RideService;
import org.example.service.RiderService;
import org.example.strategy.DefaultFareStrategy;
import org.example.strategy.FareCalculationStrategy;
import org.example.strategy.LeastActiveDriverStrategy;
import org.example.strategy.NearestDriverStrategy;
import org.example.strategy.PeakHourFareStrategy;
import org.example.strategy.RideMatchingStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DriverService driverService = new DriverService();
    private static RiderService riderService = new RiderService();
    private static RideMatchingStrategy matchingStrategy = new NearestDriverStrategy();
    private static FareCalculationStrategy fareStrategy = new DefaultFareStrategy();
    private static RideService rideService = new RideService(driverService, riderService, matchingStrategy,
            fareStrategy);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    Welcome to RideWise System");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addRider();
                    break;
                case 2:
                    addDriver();
                    break;
                case 3:
                    viewAvailableDrivers();
                    break;
                case 4:
                    viewAllDrivers();
                    break;
                case 5:
                    viewAllRiders();
                    break;
                case 6:
                    requestRide();
                    break;
                case 7:
                    completeRide();
                    break;
                case 8:
                    cancelRide();
                    break;
                case 9:
                    viewAllRides();
                    break;
                case 10:
                    searchRiders();
                    break;
                case 11:
                    searchDrivers();
                    break;
                case 12:
                    searchRides();
                    break;
                case 13:
                    changeMatchingStrategy();
                    break;
                case 14:
                    changeFareStrategy();
                    break;
                case 15:
                    viewRideDetails();
                    break;
                case 0:
                    System.out.println("Thank you for using RideWise. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1.  Add Rider");
        System.out.println("2.  Add Driver");
        System.out.println("3.  View Available Drivers");
        System.out.println("4.  View All Drivers");
        System.out.println("5.  View All Riders");
        System.out.println("6.  Request Ride");
        System.out.println("7.  Complete Ride");
        System.out.println("8.  Cancel Ride");
        System.out.println("9.  View All Rides");
        System.out.println("10. Search Riders");
        System.out.println("11. Search Drivers");
        System.out.println("12. Search Rides");
        System.out.println("13. Change Matching Strategy");
        System.out.println("14. Change Fare Strategy");
        System.out.println("15. View Ride Details");
        System.out.println("0.  Exit");
        System.out.println("===============================");
    }

    private static void addRider() {
        System.out.println("\n=== Add New Rider ===");
        try {
            String name = getStringInput("Enter rider name: ");
            String email = getStringInput("Enter email: ");
            String phone = getStringInput("Enter phone: ");
            double lat = getDoubleInput("Enter latitude: ");
            double lon = getDoubleInput("Enter longitude: ");

            Location location = new Location(lat, lon);
            Rider rider = new Rider(name, email, phone, location);
            riderService.registerRider(rider);
            System.out.println("✓ Rider added successfully!");
            System.out.println("Rider ID: " + rider.getRiderId());
        } catch (Exception e) {
            System.out.println("✗ Error adding rider: " + e.getMessage());
        }
    }

    private static void addDriver() {
        System.out.println("\n=== Add New Driver ===");
        try {
            String name = getStringInput("Enter driver name: ");
            String email = getStringInput("Enter email: ");
            String phone = getStringInput("Enter phone: ");
            double lat = getDoubleInput("Enter latitude: ");
            double lon = getDoubleInput("Enter longitude: ");
            boolean available = getBooleanInput("Is driver available? (true/false): ");

            Location location = new Location(lat, lon);
            Driver driver = new Driver(name, email, phone, location, available);
            driverService.registerDriver(driver);
            System.out.println("✓ Driver added successfully!");
            System.out.println("Driver ID: " + driver.getDriverId());
        } catch (Exception e) {
            System.out.println("✗ Error adding driver: " + e.getMessage());
        }
    }

    private static void viewAvailableDrivers() {
        System.out.println("\n=== Available Drivers ===");
        List<Driver> drivers = driverService.getAvailableDrivers();
        if (drivers.isEmpty()) {
            System.out.println("No available drivers at the moment.");
        } else {
            System.out.println("Total Available: " + drivers.size());
            for (Driver driver : drivers) {
                driver.display();
            }
        }
    }

    private static void viewAllDrivers() {
        System.out.println("\n=== All Drivers ===");
        List<Driver> drivers = driverService.getAllDrivers();
        if (drivers.isEmpty()) {
            System.out.println("No drivers registered.");
        } else {
            System.out.println("Total Drivers: " + drivers.size());
            for (Driver driver : drivers) {
                driver.display();
            }
        }
    }

    private static void viewAllRiders() {
        System.out.println("\n=== All Riders ===");
        List<Rider> riders = riderService.getAllRiders();
        if (riders.isEmpty()) {
            System.out.println("No riders registered.");
        } else {
            System.out.println("Total Riders: " + riders.size());
            for (Rider rider : riders) {
                rider.display();
            }
        }
    }

    private static void requestRide() {
        System.out.println("\n=== Request Ride ===");
        try {
            String riderId = getStringInput("Enter rider ID: ");

            System.out.println("Pickup Location:");
            double pickupLat = getDoubleInput("  Latitude: ");
            double pickupLon = getDoubleInput("  Longitude: ");
            Location pickup = new Location(pickupLat, pickupLon);

            System.out.println("Drop-off Location:");
            double dropoffLat = getDoubleInput("  Latitude: ");
            double dropoffLon = getDoubleInput("  Longitude: ");
            Location dropoff = new Location(dropoffLat, dropoffLon);

            System.out.println("Vehicle Types: BIKE, AUTO, CAR, BUS");
            String vehicleTypeStr = getStringInput("Enter vehicle type: ").toUpperCase();
            VehicleType vehicleType = VehicleType.valueOf(vehicleTypeStr);

            Ride ride = rideService.requestRide(riderId, pickup, dropoff, vehicleType);
            System.out.println("✓ Ride requested successfully!");
            ride.display();
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Invalid input: " + e.getMessage());
        } catch (RiderNotFoundException e) {
            System.out.println("✗ Rider not found: " + e.getMessage());
        } catch (NoDriverAvailableException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error requesting ride: " + e.getMessage());
        }
    }

    private static void completeRide() {
        System.out.println("\n=== Complete Ride ===");
        try {
            String rideId = getStringInput("Enter ride ID: ");
            FareReceipt receipt = rideService.completeRide(rideId);
            System.out.println("✓ Ride completed successfully!");
            System.out.println("\n=== Fare Receipt ===");
            receipt.display();
        } catch (RideNotFoundException e) {
            System.out.println("✗ Ride not found: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error completing ride: " + e.getMessage());
        }
    }

    private static void cancelRide() {
        System.out.println("\n=== Cancel Ride ===");
        try {
            String rideId = getStringInput("Enter ride ID: ");
            rideService.cancelRide(rideId);
            System.out.println("✓ Ride cancelled successfully!");
        } catch (RideNotFoundException e) {
            System.out.println("✗ Ride not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error cancelling ride: " + e.getMessage());
        }
    }

    private static void viewAllRides() {
        System.out.println("\n=== All Rides ===");
        List<Ride> rides = rideService.getAllRides();
        if (rides.isEmpty()) {
            System.out.println("No rides found.");
        } else {
            System.out.println("Total Rides: " + rides.size());
            for (Ride ride : rides) {
                ride.display();
            }
        }
    }

    private static void viewRideDetails() {
        System.out.println("\n=== View Ride Details ===");
        try {
            String rideId = getStringInput("Enter ride ID: ");
            Ride ride = rideService.getRide(rideId);
            ride.display();
        } catch (RideNotFoundException e) {
            System.out.println("✗ Ride not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private static void searchRiders() {
        System.out.println("\n=== Search Riders ===");
        String keyword = getStringInput("Enter search keyword: ");
        List<Rider> riders = riderService.getAllRiders();
        boolean found = false;
        for (Rider rider : riders) {
            if (rider.matchesSearchCriteria(keyword)) {
                rider.displaySearchResult();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No riders found matching: " + keyword);
        }
    }

    private static void searchDrivers() {
        System.out.println("\n=== Search Drivers ===");
        String keyword = getStringInput("Enter search keyword: ");
        List<Driver> drivers = driverService.getAllDrivers();
        boolean found = false;
        for (Driver driver : drivers) {
            if (driver.matchesSearchCriteria(keyword)) {
                driver.displaySearchResult();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No drivers found matching: " + keyword);
        }
    }

    private static void searchRides() {
        System.out.println("\n=== Search Rides ===");
        String keyword = getStringInput("Enter search keyword: ");
        List<Ride> rides = rideService.getAllRides();
        boolean found = false;
        for (Ride ride : rides) {
            if (ride.matchesSearchCriteria(keyword)) {
                ride.displaySearchResult();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rides found matching: " + keyword);
        }
    }

    private static void changeMatchingStrategy() {
        System.out.println("\n=== Change Matching Strategy ===");
        System.out.println("1. Nearest Driver Strategy");
        System.out.println("2. Least Active Driver Strategy");
        int choice = getIntInput("Select strategy: ");

        switch (choice) {
            case 1:
                matchingStrategy = new NearestDriverStrategy();
                System.out.println("✓ Changed to Nearest Driver Strategy");
                break;
            case 2:
                matchingStrategy = new LeastActiveDriverStrategy();
                System.out.println("✓ Changed to Least Active Driver Strategy");
                break;
            default:
                System.out.println("✗ Invalid choice");
                return;
        }

        // Recreate RideService with new strategy
        rideService = new RideService(driverService, riderService, matchingStrategy, fareStrategy);
    }

    private static void changeFareStrategy() {
        System.out.println("\n=== Change Fare Strategy ===");
        System.out.println("1. Default Fare Strategy");
        System.out.println("2. Peak Hour Fare Strategy");
        int choice = getIntInput("Select strategy: ");

        switch (choice) {
            case 1:
                fareStrategy = new DefaultFareStrategy();
                System.out.println("✓ Changed to Default Fare Strategy");
                break;
            case 2:
                fareStrategy = new PeakHourFareStrategy();
                System.out.println("✓ Changed to Peak Hour Fare Strategy");
                break;
            default:
                System.out.println("✗ Invalid choice");
                return;
        }

        // Recreate RideService with new strategy
        rideService = new RideService(driverService, riderService, matchingStrategy, fareStrategy);
    }

    // Helper methods for input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static boolean getBooleanInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt).toLowerCase();
            if (input.equals("true") || input.equals("t") || input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("false") || input.equals("f") || input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Please enter 'true' or 'false'");
            }
        }
    }
}
