package com.example.travel_planner.domain.city;

import com.example.travel_planner.domain.city.CityCostLevel;

import java.util.Set;

public class CityCostService {

    private static final Set<String> HIGH_COST_CITIES = Set.of(
            "london",
            "paris",
            "new york",
            "zurich",
            "singapore",
            "tokyo"
    );

    private static final Set<String> LOW_COST_CITIES = Set.of(
            "goa",
            "jaipur",
            "varanasi",
            "udaipur",
            "rishikesh"
    );

    public CityCostLevel getCityCostLevel(String city) {

        if (city == null) {
            return CityCostLevel.MEDIUM;
        }

        String normalized = city.toLowerCase().trim();

        if (HIGH_COST_CITIES.contains(normalized)) {
            return CityCostLevel.HIGH;
        }

        if (LOW_COST_CITIES.contains(normalized)) {
            return CityCostLevel.LOW;
        }

        return CityCostLevel.MEDIUM;
    }
}