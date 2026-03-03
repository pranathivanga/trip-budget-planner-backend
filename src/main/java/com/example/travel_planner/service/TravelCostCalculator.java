package com.example.travel_planner.service;

import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.domain.cost.Money;

public class TravelCostCalculator {

    private static final double COST_PER_KM = 5.0;

    private final DistanceCalculator distanceCalculator = new DistanceCalculator();

    public Money calculate(Trip trip) {

        int distance = distanceCalculator.calculateDistance(
                trip.getSource().getCity(),
                trip.getDestination().getCity()
        );

        double total = distance
                * COST_PER_KM
                * trip.getNumberOfTravelers();

        return new Money(total, "INR");
    }
}