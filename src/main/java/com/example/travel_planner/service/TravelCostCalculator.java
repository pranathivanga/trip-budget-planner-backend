package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Trip;

public class TravelCostCalculator {
    private static final double COST_PER_TRAVELER = 2000;

    public Money calculate(Trip trip) {
        double total = COST_PER_TRAVELER * trip.getNumberOfTravelers();
        return new Money(total, "INR");
    }
}
