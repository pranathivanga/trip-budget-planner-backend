package com.example.travel_planner.service;

import com.example.travel_planner.domain.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BudgetFeasibilityServiceTest {
    @Test
    void shouldReturnFeasibleWhenBudgetIsEnough() {

        Budget budget = new Budget(new Money(10000, "INR"));
        Money totalCost = new Money(6000, "INR");

        BudgetFeasibilityService service = new BudgetFeasibilityService();

        FeasibilityResult result = service.evaluate(budget, totalCost);

        assertTrue(result.isFeasible());
    }

    @Test
    void shouldReturnNotFeasibleWhenBudgetIsInsufficient() {

        Budget budget = new Budget(new Money(5000, "INR"));
        Money totalCost = new Money(6000, "INR");

        BudgetFeasibilityService service = new BudgetFeasibilityService();

        FeasibilityResult result = service.evaluate(budget, totalCost);

        assertFalse(result.isFeasible());
    }
}
