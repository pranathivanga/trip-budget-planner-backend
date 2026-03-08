package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Trip;

public class FoodCostCalculator {

    public Money calculate(Trip trip) {

        double costPerDay = 400;

        double total =
                costPerDay *
                        trip.getNumberOfDays() *
                        trip.getNumberOfTravelers();

        return new Money(total, "INR");
    }
}