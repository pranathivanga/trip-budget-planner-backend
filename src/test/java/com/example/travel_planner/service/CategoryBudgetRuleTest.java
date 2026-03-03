package com.example.travel_planner.service;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.RuleViolation;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryBudgetRuleTest {

    @Test
    void shouldReturnViolationWhenTravelBudgetExceeded() {

        CategoryBudgetRule rule = new CategoryBudgetRule();

        Money travelCost = new Money(8000, "INR");
        Money allowedBudget = new Money(5000, "INR");

        Optional<RuleViolation> violation =
                Optional.ofNullable(
                        rule.check("travel", travelCost, allowedBudget)
                );

        assertTrue(violation.isPresent());
    }

    @Test
    void shouldPassWhenWithinBudget() {

        CategoryBudgetRule rule = new CategoryBudgetRule();

        Money travelCost = new Money(4000, "INR");
        Money allowedBudget = new Money(5000, "INR");

        Optional<RuleViolation> violation =
                Optional.ofNullable(
                        rule.check("travel", travelCost, allowedBudget)
                );

        assertFalse(violation.isPresent());
    }
}
