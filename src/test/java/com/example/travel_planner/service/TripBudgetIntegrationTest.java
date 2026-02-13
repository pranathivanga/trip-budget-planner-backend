package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripBudgetIntegrationTest {
    @Test
    void shouldDetermineTripAsFeasibleWithinBudget() {

        // 1. Trip input
        Trip trip = new Trip(
                new Location("Hyderabad"),
                new Location("Goa"),
                3,
                2
        );

        // 2. Calculate total cost
        TotalCostCalculator totalCostCalculator = new TotalCostCalculator();
        Money totalCost = totalCostCalculator.calculateTotalCost(trip);

        // 3. Budget
        Budget budget = new Budget(new Money(25000, "INR"));

        // 4. Feasibility decision
        BudgetFeasibilityService feasibilityService =
                new BudgetFeasibilityService();

        FeasibilityResult result =
                feasibilityService.evaluate(budget, totalCost);

        // 5. Assertions (final truth)
        assertTrue(result.isFeasible());
        assertEquals(16000, result.getTotalCost().getAmount());
    }
}
