package org.example;

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

/**
 * DEMO CLASS - Comprehensive demonstration of all RideWise features
 * This class demonstrates:
 * - Rider and Driver registration
 * - Ride requesting with different strategies
 * - Ride completion and fare calculation
 * - Ride cancellation
 * - Search functionality
 * - Strategy switching
 * - Viewing all entities
 */
public class Demo {

    private static DriverService driverService = new DriverService();
    private static RiderService riderService = new RiderService();
    private static RideMatchingStrategy matchingStrategy = new NearestDriverStrategy();
    private static FareCalculationStrategy fareStrategy = new DefaultFareStrategy();
    private static RideService rideService = new RideService(
            driverService, riderService, matchingStrategy, fareStrategy);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         RIDEWISE SYSTEM - COMPREHENSIVE DEMO                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();

        try {
            // Demo 1: Register Riders
            demoRegisterRiders();

            // Demo 2: Register Drivers
            demoRegisterDrivers();

            // Demo 3: View All Registered Entities
            demoViewAllEntities();

            // Demo 4: Request Rides with Nearest Driver Strategy
            demoRequestRidesNearestStrategy();

            // Demo 5: View Available Drivers
            demoViewAvailableDrivers();

            // Demo 6: Complete Rides
            demoCompleteRides();

            // Demo 7: Search Functionality
            demoSearchFunctionality();

            // Demo 8: Change to Least Active Driver Strategy
            demoChangeMatchingStrategy();

            // Demo 9: Request More Rides with Different Strategy
            demoRequestRidesLeastActiveStrategy();

            // Demo 10: Change to Peak Hour Fare Strategy
            demoChangeFareStrategy();

            // Demo 11: Complete Ride with Peak Hour Pricing
            demoPeakHourPricing();

            // Demo 12: Cancel Ride
            demoCancelRide();

            // Demo 13: Final View of All Data
            demoFinalView();

            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║              DEMO COMPLETED SUCCESSFULLY!                ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");

        } catch (Exception e) {
            System.out.println("\n✗ Error during demo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demoRegisterRiders() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 1: Registering Riders");
        System.out.println("═══════════════════════════════════════════════════════════════");

        Rider rider1 = new Rider("Rajesh Kumar", "rajesh.kumar@email.com", "98765-43210",
                new Location(28.6139, 77.2090)); // New Delhi
        Rider rider2 = new Rider("Priya Sharma", "priya.sharma@email.com", "98765-43211",
                new Location(19.0760, 72.8777)); // Mumbai
        Rider rider3 = new Rider("Amit Patel", "amit.patel@email.com", "98765-43212",
                new Location(12.9716, 77.5946)); // Bangalore

        riderService.registerRider(rider1);
        System.out.println("✓ Registered Rider: " + rider1.getName() + " (ID: " + rider1.getRiderId() + ")");

        riderService.registerRider(rider2);
        System.out.println("✓ Registered Rider: " + rider2.getName() + " (ID: " + rider2.getRiderId() + ")");

        riderService.registerRider(rider3);
        System.out.println("✓ Registered Rider: " + rider3.getName() + " (ID: " + rider3.getRiderId() + ")");

        System.out.println("Total Riders: " + riderService.getAllRiders().size());
        System.out.println();
    }

    private static void demoRegisterDrivers() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 2: Registering Drivers");
        System.out.println("═══════════════════════════════════════════════════════════════");

        Driver driver1 = new Driver("Ravi Singh", "ravi.singh@email.com", "98765-12345",
                new Location(28.7041, 77.1025), true); // Near New Delhi
        Driver driver2 = new Driver("Kavita Reddy", "kavita.reddy@email.com", "98765-12346",
                new Location(19.0760, 72.8777), true); // Mumbai
        Driver driver3 = new Driver("Deepak Gupta", "deepak.gupta@email.com", "98765-12347",
                new Location(12.9716, 77.5946), true); // Bangalore
        Driver driver4 = new Driver("Sunita Mehta", "sunita.mehta@email.com", "98765-12348",
                new Location(28.6139, 77.2090), true); // New Delhi
        Driver driver5 = new Driver("Vikram Joshi", "vikram.joshi@email.com", "98765-12349",
                new Location(28.7041, 77.1025), false); // New Delhi (Unavailable)

        driverService.registerDriver(driver1);
        System.out.println(
                "✓ Registered Driver: " + driver1.getName() + " (ID: " + driver1.getDriverId() + ") - Available");

        driverService.registerDriver(driver2);
        System.out.println(
                "✓ Registered Driver: " + driver2.getName() + " (ID: " + driver2.getDriverId() + ") - Available");

        driverService.registerDriver(driver3);
        System.out.println(
                "✓ Registered Driver: " + driver3.getName() + " (ID: " + driver3.getDriverId() + ") - Available");

        driverService.registerDriver(driver4);
        System.out.println(
                "✓ Registered Driver: " + driver4.getName() + " (ID: " + driver4.getDriverId() + ") - Available");

        driverService.registerDriver(driver5);
        System.out.println(
                "✓ Registered Driver: " + driver5.getName() + " (ID: " + driver5.getDriverId() + ") - Unavailable");

        System.out.println("Total Drivers: " + driverService.getAllDrivers().size());
        System.out.println("Available Drivers: " + driverService.getAvailableDrivers().size());
        System.out.println();
    }

    private static void demoViewAllEntities() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 3: Viewing All Registered Entities");
        System.out.println("═══════════════════════════════════════════════════════════════");

        System.out.println("\n--- All Riders ---");
        List<Rider> riders = riderService.getAllRiders();
        for (Rider rider : riders) {
            rider.display();
        }

        System.out.println("\n--- All Drivers ---");
        List<Driver> drivers = driverService.getAllDrivers();
        for (Driver driver : drivers) {
            driver.display();
        }
        System.out.println();
    }

    private static void demoRequestRidesNearestStrategy() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 4: Requesting Rides (Nearest Driver Strategy)");
        System.out.println("═══════════════════════════════════════════════════════════════");

        List<Rider> riders = riderService.getAllRiders();
        if (riders.isEmpty()) {
            System.out.println("No riders available for demo");
            return;
        }

        Rider rider1 = riders.get(0);

        // Request ride 1: CAR from New Delhi to nearby location
        Location pickup1 = new Location(28.6139, 77.2090);
        Location dropoff1 = new Location(28.7041, 77.1025);

        try {
            Ride ride1 = rideService.requestRide(rider1.getRiderId(), pickup1, dropoff1, VehicleType.CAR);
            System.out.println("✓ Ride Requested Successfully!");
            System.out.println("  Ride ID: " + ride1.getRideId());
            System.out.println("  Rider: " + ride1.getRider().getName());
            System.out.println("  Driver: " + ride1.getDriver().getName());
            System.out.println("  Distance: " + String.format("%.2f", ride1.getDistance()) + " km");
            System.out.println("  Vehicle: " + ride1.getVehicleType());
            System.out.println("  Status: " + ride1.getRideStatus());
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        // Request ride 2: BIKE ride
        if (riders.size() > 1) {
            Rider rider2 = riders.get(1);
            Location pickup2 = new Location(19.0760, 72.8777);
            Location dropoff2 = new Location(19.2183, 72.9781);

            try {
                Ride ride2 = rideService.requestRide(rider2.getRiderId(), pickup2, dropoff2, VehicleType.BIKE);
                System.out.println("\n✓ Ride Requested Successfully!");
                System.out.println("  Ride ID: " + ride2.getRideId());
                System.out.println("  Rider: " + ride2.getRider().getName());
                System.out.println("  Driver: " + ride2.getDriver().getName());
                System.out.println("  Distance: " + String.format("%.2f", ride2.getDistance()) + " km");
                System.out.println("  Vehicle: " + ride2.getVehicleType());
                System.out.println("  Status: " + ride2.getRideStatus());
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }

        System.out.println();
    }

    private static void demoViewAvailableDrivers() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 5: Viewing Available Drivers");
        System.out.println("═══════════════════════════════════════════════════════════════");

        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        System.out.println("Available Drivers Count: " + availableDrivers.size());

        if (!availableDrivers.isEmpty()) {
            System.out.println("\nAvailable Drivers:");
            for (Driver driver : availableDrivers) {
                System.out.println("  - " + driver.getName() + " (ID: " + driver.getDriverId() + ")");
            }
        } else {
            System.out.println("No drivers available (all are on rides)");
        }
        System.out.println();
    }

    private static void demoCompleteRides() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 6: Completing Rides");
        System.out.println("═══════════════════════════════════════════════════════════════");

        List<Ride> rides = rideService.getAllRides();
        if (rides.isEmpty()) {
            System.out.println("No rides to complete");
            return;
        }

        for (Ride ride : rides) {
            if (ride.getRideStatus() == RideStatus.ASSIGNED) {
                try {
                    FareReceipt receipt = rideService.completeRide(ride.getRideId());
                    System.out.println("✓ Ride Completed!");
                    System.out.println("  Ride ID: " + ride.getRideId());
                    System.out.println("  Driver: " + ride.getDriver().getName());
                    System.out.println("\n  --- Fare Receipt ---");
                    receipt.display();
                    System.out.println();
                } catch (Exception e) {
                    System.out.println("✗ Error completing ride " + ride.getRideId() + ": " + e.getMessage());
                }
            }
        }
        System.out.println();
    }

    private static void demoSearchFunctionality() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 7: Search Functionality");
        System.out.println("═══════════════════════════════════════════════════════════════");

        // Search Riders
        System.out.println("\n--- Searching Riders for 'Rajesh' ---");
        List<Rider> riders = riderService.getAllRiders();
        boolean found = false;
        for (Rider rider : riders) {
            if (rider.matchesSearchCriteria("Rajesh")) {
                rider.displaySearchResult();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No riders found matching 'Rajesh'");
        }

        // Search Drivers
        System.out.println("\n--- Searching Drivers for 'Ravi' ---");
        List<Driver> drivers = driverService.getAllDrivers();
        found = false;
        for (Driver driver : drivers) {
            if (driver.matchesSearchCriteria("Ravi")) {
                driver.displaySearchResult();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No drivers found matching 'Ravi'");
        }

        // Search Rides
        System.out.println("\n--- Searching Rides for 'CAR' ---");
        List<Ride> rides = rideService.getAllRides();
        found = false;
        for (Ride ride : rides) {
            if (ride.matchesSearchCriteria("CAR")) {
                ride.displaySearchResult();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rides found matching 'CAR'");
        }

        System.out.println();
    }

    private static void demoChangeMatchingStrategy() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 8: Changing Matching Strategy");
        System.out.println("═══════════════════════════════════════════════════════════════");

        System.out.println("Current Strategy: Nearest Driver Strategy");
        System.out.println("Changing to: Least Active Driver Strategy");

        matchingStrategy = new LeastActiveDriverStrategy();
        rideService = new RideService(driverService, riderService, matchingStrategy, fareStrategy);

        System.out.println("✓ Strategy changed successfully!");
        System.out.println("  New Strategy: Least Active Driver Strategy");
        System.out.println("  (Selects driver with fewest completed rides)");
        System.out.println();
    }

    private static void demoRequestRidesLeastActiveStrategy() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 9: Requesting Rides (Least Active Driver Strategy)");
        System.out.println("═══════════════════════════════════════════════════════════════");

        List<Rider> riders = riderService.getAllRiders();
        if (riders.isEmpty()) {
            System.out.println("No riders available");
            return;
        }

        Rider rider = riders.size() > 2 ? riders.get(2) : riders.get(0);

        Location pickup = new Location(12.9716, 77.5946);
        Location dropoff = new Location(12.9352, 77.6245);

        try {
            Ride ride = rideService.requestRide(rider.getRiderId(), pickup, dropoff, VehicleType.AUTO);
            System.out.println("✓ Ride Requested with Least Active Driver Strategy!");
            System.out.println("  Ride ID: " + ride.getRideId());
            System.out.println("  Rider: " + ride.getRider().getName());
            System.out.println("  Driver: " + ride.getDriver().getName());
            System.out.println("  Driver's Completed Rides: " + ride.getDriver().getCompletedRidesCount());
            System.out.println("  Distance: " + String.format("%.2f", ride.getDistance()) + " km");
            System.out.println("  Vehicle: " + ride.getVehicleType());
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }

        System.out.println();
    }

    private static void demoChangeFareStrategy() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 10: Changing Fare Strategy");
        System.out.println("═══════════════════════════════════════════════════════════════");

        System.out.println("Current Strategy: Default Fare Strategy");
        System.out.println("Changing to: Peak Hour Fare Strategy");

        fareStrategy = new PeakHourFareStrategy();
        rideService = new RideService(driverService, riderService, matchingStrategy, fareStrategy);

        System.out.println("✓ Strategy changed successfully!");
        System.out.println("  New Strategy: Peak Hour Fare Strategy");
        System.out.println("  (Applies 1.5x multiplier during peak hours: 7-9 AM, 5-7 PM)");
        System.out.println();
    }

    private static void demoPeakHourPricing() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 11: Peak Hour Pricing");
        System.out.println("═══════════════════════════════════════════════════════════════");

        List<Ride> rides = rideService.getAllRides();
        Ride rideToComplete = null;

        for (Ride ride : rides) {
            if (ride.getRideStatus() == RideStatus.ASSIGNED) {
                rideToComplete = ride;
                break;
            }
        }

        if (rideToComplete != null) {
            try {
                System.out.println("Completing ride with Peak Hour Fare Strategy...");
                FareReceipt receipt = rideService.completeRide(rideToComplete.getRideId());
                System.out.println("✓ Ride Completed with Peak Hour Pricing!");
                System.out.println("\n  --- Fare Receipt ---");
                receipt.display();

                // Show comparison
                DefaultFareStrategy defaultStrategy = new DefaultFareStrategy();
                double defaultFare = defaultStrategy.calculateFare(rideToComplete);
                System.out.println("\n  Price Comparison:");
                System.out.println("    Default Fare: ₹" + String.format("%.2f", defaultFare));
                System.out.println("    Peak Hour Fare: ₹" + String.format("%.2f", receipt.getAmount()));
                if (receipt.getAmount() > defaultFare) {
                    System.out.println("    (Peak hour multiplier applied!)");
                } else {
                    System.out.println("    (Not peak hour - standard rate)");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        } else {
            System.out.println("No assigned rides to complete");
        }

        System.out.println();
    }

    private static void demoCancelRide() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 12: Cancelling a Ride");
        System.out.println("═══════════════════════════════════════════════════════════════");

        // First, request a new ride to cancel
        List<Rider> riders = riderService.getAllRiders();
        if (!riders.isEmpty()) {
            Rider rider = riders.get(0);
            Location pickup = new Location(28.6139, 77.2090);
            Location dropoff = new Location(28.7041, 77.1025);

            try {
                Ride ride = rideService.requestRide(rider.getRiderId(), pickup, dropoff, VehicleType.BUS);
                System.out.println("✓ Ride Requested for cancellation demo");
                System.out.println("  Ride ID: " + ride.getRideId());
                System.out.println("  Status: " + ride.getRideStatus());

                // Now cancel it
                System.out.println("\nCancelling the ride...");
                rideService.cancelRide(ride.getRideId());
                System.out.println("✓ Ride Cancelled Successfully!");

                Ride cancelledRide = rideService.getRide(ride.getRideId());
                System.out.println("  Final Status: " + cancelledRide.getRideStatus());
                System.out.println("  Driver Available: " + cancelledRide.getDriver().isAvailable());
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }

        System.out.println();
    }

    private static void demoFinalView() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("DEMO 13: Final View of All Data");
        System.out.println("═══════════════════════════════════════════════════════════════");

        System.out.println("\n--- All Riders (" + riderService.getAllRiders().size() + ") ---");
        for (Rider rider : riderService.getAllRiders()) {
            rider.display();
        }

        System.out.println("\n--- All Drivers (" + driverService.getAllDrivers().size() + ") ---");
        for (Driver driver : driverService.getAllDrivers()) {
            driver.display();
        }

        System.out.println("\n--- All Rides (" + rideService.getAllRides().size() + ") ---");
        for (Ride ride : rideService.getAllRides()) {
            ride.display();
        }

        System.out.println("\n--- Summary Statistics ---");
        System.out.println("Total Riders: " + riderService.getAllRiders().size());
        System.out.println("Total Drivers: " + driverService.getAllDrivers().size());
        System.out.println("Available Drivers: " + driverService.getAvailableDrivers().size());
        System.out.println("Total Rides: " + rideService.getAllRides().size());

        long completedRides = rideService.getAllRides().stream()
                .filter(r -> r.getRideStatus() == RideStatus.COMPLETED)
                .count();
        long cancelledRides = rideService.getAllRides().stream()
                .filter(r -> r.getRideStatus() == RideStatus.CANCELLED)
                .count();
        long assignedRides = rideService.getAllRides().stream()
                .filter(r -> r.getRideStatus() == RideStatus.ASSIGNED)
                .count();

        System.out.println("Completed Rides: " + completedRides);
        System.out.println("Cancelled Rides: " + cancelledRides);
        System.out.println("Assigned Rides: " + assignedRides);

        System.out.println();
    }
}
