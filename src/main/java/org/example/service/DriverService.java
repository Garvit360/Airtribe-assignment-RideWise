package org.example.service;

import org.example.exception.DriverNotFoundException;
import org.example.model.Driver;
import org.example.util.DataStore;
import org.example.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class DriverService {

    private DataStore<Driver> driverStore;

    public DriverService() {
        this.driverStore = new DataStore<Driver>();
    }

    public void registerDriver(Driver driver) {
        Validator.validDriver(driver);
        driverStore.add(driver.getDriverId(), driver);
    }

    public Driver getDriver(String driverId) throws DriverNotFoundException {
        Driver driver = driverStore.findById(driverId);

        if (driver == null) {
            throw new DriverNotFoundException(driverId);
        }
        return driver;
    }

    public void updateDriver(Driver driver) {
        Validator.validDriver(driver);
        driverStore.update(driver.getDriverId(), driver);
    }

    public void deleteDriver(String driverId) {
        driverStore.delete(driverId);
    }

    public List<Driver> getAllDrivers() {
        return new ArrayList<>(driverStore.getAll());
    }

    public List<Driver> getAvailableDrivers() {
        List<Driver> allDrivers = getAllDrivers();
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : allDrivers) {
            if (driver != null && driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }
}
