package com.example.travel_planner;

import com.example.travel_planner.domain.trip.DestinationCategory;

import java.util.Set;

public class DestinationClassifier {

    private static final Set<String> HIGH_COST = Set.of("Goa", "Delhi");
    private static final Set<String> MEDIUM_COST = Set.of("Bangalore");

    public DestinationCategory classify(String city) {

        if (HIGH_COST.contains(city)) {
            return DestinationCategory.HIGH;
        }

        if (MEDIUM_COST.contains(city)) {
            return DestinationCategory.MEDIUM;
        }

        return DestinationCategory.LOW;
    }
}