package org.example.strategy;

import org.example.model.Ride;

public interface FareCalculationStrategy {

    double calculateFare(Ride ride);
}
