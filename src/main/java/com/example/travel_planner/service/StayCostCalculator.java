package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Trip;

public class StayCostCalculator {
    private static final double COST_PER_DAY = 1500;

    public Money calculate(Trip trip) {
        double total =
                COST_PER_DAY *
                        trip.getNumberOfDays() *
                        trip.getNumberOfTravelers();

        return new Money(total, "INR");
    }
}
