package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItineraryGeneratorTest {

    @Test
    void shouldGenerateThreePlans() {

        Trip trip = new Trip(
                new Location("Hyderabad"),
                new Location("Goa"),
                3,
                2,
                StayPreference.STANDARD
        );

        Budget budget = new Budget(
                new Money(20000, "INR"),
                new Money(40000, "INR"),
                new Money(15000, "INR")
        );

        ItineraryGenerator generator = new ItineraryGenerator();

        List<TripPlan> plans = generator.generatePlans(trip, budget);

        assertEquals(3, plans.size());
    }
}