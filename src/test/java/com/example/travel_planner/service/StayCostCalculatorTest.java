package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StayCostCalculatorTest {
    @Test
    void shouldCalculateStayCostBasedOnDaysAndTravelers() {

        Trip trip = new Trip(
                new Location("Hyderabad"),
                new Location("Goa"),
                4,
                2
        );

        StayCostCalculator calculator = new StayCostCalculator();

        Money cost = calculator.calculate(trip);

        assertEquals(12000, cost.getAmount());
    }
}
