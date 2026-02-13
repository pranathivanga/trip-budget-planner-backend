package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalCostCalculatorTest {
    @Test
    void shouldCalculateTotalCostAsSumOfAllCosts() {

        Trip trip = new Trip(
                new Location("Hyderabad"),
                new Location("Delhi"),
                2,
                1
        );

        TotalCostCalculator calculator = new TotalCostCalculator();

        Money totalCost = calculator.calculateTotalCost(trip);

        assertEquals(6000, totalCost.getAmount());
    }
}
