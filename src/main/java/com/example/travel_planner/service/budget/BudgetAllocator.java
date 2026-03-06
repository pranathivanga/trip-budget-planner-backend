package com.example.travel_planner.service.budget;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.trip.Trip;

public class BudgetAllocator {

    public Budget allocate(double totalBudget, Trip trip) {

        double travel = totalBudget * 0.30;
        double stay = totalBudget * 0.50;
        double food = totalBudget * 0.20;

        return new Budget(
                new Money(totalBudget, "INR"),
                new Money(travel, "INR"),
                new Money(stay, "INR"),
                new Money(food, "INR")
        );
    }
}