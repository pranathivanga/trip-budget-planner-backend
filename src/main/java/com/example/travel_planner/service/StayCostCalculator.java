package com.example.travel_planner.service;

import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.domain.trip.DestinationCategory;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.cost.Money;

public class StayCostCalculator {

    private final DestinationClassifier classifier = new DestinationClassifier();

    public Money calculate(Trip trip) {

        DestinationCategory category =
                classifier.classify(trip.getDestination().getCity());

        StayPreference preference = trip.getStayPreference();

        double baseCost;

        switch (category) {
            case HIGH:
                baseCost = 3000;
                break;
            case MEDIUM:
                baseCost = 2000;
                break;
            default:
                baseCost = 1200;
        }

        double multiplier;

        switch (preference) {
            case PREMIUM:
                multiplier = 1.8;
                break;
            case STANDARD:
                multiplier = 1.2;
                break;
            default:
                multiplier = 1.0;
        }

        double costPerDay = baseCost * multiplier;

        double total =
                costPerDay
                        * trip.getNumberOfDays()
                        * trip.getNumberOfTravelers();

        return new Money(total, "INR");
    }
}