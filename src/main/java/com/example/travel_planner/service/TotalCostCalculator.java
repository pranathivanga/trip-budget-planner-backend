package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Trip;

public class TotalCostCalculator {

    private final TravelCostCalculator travelCostCalculator;
    private final StayCostCalculator stayCostCalculator;
    private final FoodCostCalculator foodCostCalculator;

    public TotalCostCalculator() {
        this.travelCostCalculator = new TravelCostCalculator();
        this.stayCostCalculator = new StayCostCalculator();
        this.foodCostCalculator = new FoodCostCalculator();
    }

    public Money calculateTotalCost(Trip trip) {
        double total =
                travelCostCalculator.calculate(trip).getAmount() +
                        stayCostCalculator.calculate(trip).getAmount() +
                        foodCostCalculator.calculate(trip).getAmount();

        return new Money(total, "INR");
    }
}
