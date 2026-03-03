package com.example.travel_planner.service;

import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.domain.trip.DestinationCategory;
import com.example.travel_planner.domain.cost.Money;

public class FoodCostCalculator {

    private final DestinationClassifier classifier = new DestinationClassifier();

    public Money calculate(Trip trip) {

        DestinationCategory category =
                classifier.classify(trip.getDestination().getCity());

        double costPerDay;

        switch (category) {
            case HIGH:
                costPerDay = 800;
                break;
            case MEDIUM:
                costPerDay = 600;
                break;
            default:
                costPerDay = 400;
        }

        double total =
                costPerDay
                        * trip.getNumberOfDays()
                        * trip.getNumberOfTravelers();

        return new Money(total, "INR");
    }
}