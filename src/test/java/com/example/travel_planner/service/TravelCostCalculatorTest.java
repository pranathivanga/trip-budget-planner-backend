package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCostCalculatorTest {
    @Test
    void shouldCalculateTravelCostBasedOnNumberOfTravelers() {

        Trip trip = new Trip(
                new Location("Hyderabad"),
                new Location("Bangalore"),
                3,
                2,
                StayPreference.STANDARD

        );

        TravelCostCalculator calculator = new TravelCostCalculator();

        Money cost = calculator.calculate(trip);

        assertEquals(5700, cost.getAmount());
        assertEquals("INR", cost.getCurrency());
    }
}
