package com.example.travel_planner;

import com.example.travel_planner.service.BudgetFeasibilityService;
import com.example.travel_planner.budget.Budget;
import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetFeasibilityServiceTest {

    @Test
    public void shouldDetectBudgetFeasible() {

        BudgetFeasibilityService service = new BudgetFeasibilityService();

        Budget budget = new Budget(
                new Money(3000, "INR"),
                new Money(1000, "INR"),
                new Money(1000, "INR"),
                new Money(1000, "INR")
        );

        FeasibilityResult result =
                service.evaluate(
                        budget,
                        new Money(1000, "INR"),
                        new Money(1000, "INR"),
                        new Money(1000, "INR")
                );
System.out.println(result.isFeasible());
        assertTrue(result.isFeasible());
    }
}