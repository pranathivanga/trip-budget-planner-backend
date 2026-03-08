package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Trip;

public class StayCostCalculator {

    public Money calculate(Trip trip) {

        double costPerNight = 2500;

        double total =
                costPerNight *
                        trip.getNumberOfDays() *
                        trip.getNumberOfTravelers();

        return new Money(total, "INR");
    }
}