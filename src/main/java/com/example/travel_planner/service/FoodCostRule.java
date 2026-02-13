package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.RuleViolation;
import com.example.travel_planner.domain.trip.Trip;

import java.util.Optional;

public class FoodCostRule implements Rule {

    private static final double MIN_COST_PER_DAY = 300;

    private final Trip trip;
    private final Money foodCost;

    public FoodCostRule(Trip trip, Money foodCost) {
        this.trip = trip;
        this.foodCost = foodCost;
    }

    @Override
    public Optional<RuleViolation> check() {

        double costPerDayPerTraveler =
                foodCost.getAmount() /
                        (trip.getNumberOfDays() * trip.getNumberOfTravelers());

        if (costPerDayPerTraveler < MIN_COST_PER_DAY) {
            return Optional.of(
                    new RuleViolation(
                            "MIN_FOOD_COST",
                            "Food cost per day per traveler is too low"
                    )
            );
        }

        return Optional.empty();
    }
}