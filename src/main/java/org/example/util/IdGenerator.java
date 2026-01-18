package org.example.util;

public final class IdGenerator {

    private IdGenerator() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    private static long riderIdCounter = 0;
    private static long driverIdCounter = 0;
    private static long rideIdCounter = 0;
    private static long receiptIdCounter = 0;

     public static String generateRiderId() {
         return "RIDER"+ (++riderIdCounter);
     }

     public static String generateDriverId() {
         return "DRIVER-" + (++driverIdCounter);
     }

     public static String generateRideId() {
         return "RIDE-" + (++rideIdCounter);
     }

     public static String generateReceiptId() {
         return "RECEIPT-" + (++receiptIdCounter);
     }
}

