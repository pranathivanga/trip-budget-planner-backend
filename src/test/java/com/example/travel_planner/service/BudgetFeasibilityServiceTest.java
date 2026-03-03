package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BudgetFeasibilityServiceTest {

    @Test
    void shouldReturnFeasibleWhenAllCategoryBudgetsAreEnough() {

        Budget budget = new Budget(
                new Money(5000, "INR"),   // travel budget
                new Money(10000, "INR"),  // stay budget
                new Money(5000, "INR")    // food budget
        );

        Money travelCost = new Money(4000, "INR");
        Money stayCost = new Money(8000, "INR");
        Money foodCost = new Money(3000, "INR");

        BudgetFeasibilityService service = new BudgetFeasibilityService();

        FeasibilityResult result =
                service.evaluate(budget, travelCost, stayCost, foodCost);

        assertTrue(result.isFeasible());
    }

    @Test
    void shouldReturnNotFeasibleWhenTravelBudgetExceeded() {

        Budget budget = new Budget(
                new Money(3000, "INR"),   // travel budget too small
                new Money(10000, "INR"),
                new Money(5000, "INR")
        );

        Money travelCost = new Money(4000, "INR");  // exceeds
        Money stayCost = new Money(8000, "INR");
        Money foodCost = new Money(3000, "INR");

        BudgetFeasibilityService service = new BudgetFeasibilityService();

        FeasibilityResult result =
                service.evaluate(budget, travelCost, stayCost, foodCost);

        assertFalse(result.isFeasible());
    }
}