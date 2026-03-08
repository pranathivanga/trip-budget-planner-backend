package com.example.travel_planner;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistanceCostComparisonTest {
    @Test
    void delhiShouldCostMoreThanBangaloreFromHyderabad() {

        TravelCostCalculator travelCalc =
                new TravelCostCalculator(
                        new com.example.travel_planner.config.PlannerConfig(),
                        new com.example.travel_planner.distance.DistanceService()
                );

        Trip bangaloreTrip = new Trip(
                new Location("Hyderabad"),
                new Location("Bangalore"),
                3,
                1,
                StayPreference.STANDARD
        );

        Trip delhiTrip = new Trip(
                new Location("Hyderabad"),
                new Location("Delhi"),
                3,
                1,
                StayPreference.STANDARD
        );

        Money bangaloreCost = travelCalc.calculate(bangaloreTrip);
        Money delhiCost = travelCalc.calculate(delhiTrip);

        System.out.println("Bangalore travel cost = " + bangaloreCost.getAmount());
        System.out.println("Delhi travel cost = " + delhiCost.getAmount());

        assertTrue(
                delhiCost.getAmount() > bangaloreCost.getAmount(),
                "Delhi travel cost should be higher because distance is larger"
        );
    }
}
