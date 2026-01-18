package org.example.util;

import org.example.exception.InvalidDataException;
import org.example.model.Driver;
import org.example.model.Rider;


public class Validator {

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static void validRider(Rider rider) throws InvalidDataException {
        if (rider.getName() == null || rider.getName().trim().isEmpty())
            throw new InvalidDataException("Rider name can not be empty");

        if (rider.getEmail() == null || rider.getEmail().trim().isEmpty())
            throw new InvalidDataException("Rider email can not be empty");

        if (rider.getPhone() == null || rider.getPhone().trim().isEmpty())
            throw new InvalidDataException("Rider phone can not be empty");
    }

    public static void validDriver(Driver driver) throws InvalidDataException {
        if (driver.getName() == null || driver.getName().trim().isEmpty())
            throw new InvalidDataException("Rider name can not be empty");

        if (driver.getEmail() == null || driver.getEmail().trim().isEmpty())
            throw new InvalidDataException("Rider email can not be empty");

        if (driver.getPhone() == null || driver.getPhone().trim().isEmpty())
            throw new InvalidDataException("Rider phone can not be empty");
    }

}
