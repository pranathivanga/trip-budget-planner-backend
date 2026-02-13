package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.RuleViolation;
import com.example.travel_planner.domain.trip.Trip;

public class FoodCostRule {
    private static final double MIN_COST_PER_DAY = 300;

    public RuleViolation check(Trip trip, Money foodCost) {

        double costPerDayPerTraveler =
                foodCost.getAmount() /
                        (trip.getNumberOfDays() * trip.getNumberOfTravelers());

        if (costPerDayPerTraveler < MIN_COST_PER_DAY) {
            return new RuleViolation(
                    "MIN_FOOD_COST",
                    "Food cost per day per traveler is too low"
            );
        }

        return null; // no violation
    }
}
