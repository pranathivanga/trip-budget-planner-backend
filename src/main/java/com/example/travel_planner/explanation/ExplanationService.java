package com.example.travel_planner.explanation;

import com.example.travel_planner.domain.plan.PlanType;
import com.example.travel_planner.domain.decision.BudgetState;
import com.example.travel_planner.domain.plan.TripPlan;

public class ExplanationService {

    public String generateExplanation(TripPlan plan) {

        PlanType type = plan.getPlanType();
        BudgetState state = plan.getBudgetState();

        if (type == PlanType.BUDGET) {
            return "This plan prioritizes affordability by selecting budget accommodations while keeping food and travel expenses minimal.";
        }

        if (type == PlanType.BALANCED) {
            return "This plan balances comfort and cost by choosing standard accommodation while maintaining reasonable travel and dining expenses.";
        }

        if (type == PlanType.COMFORT) {
            return "This plan focuses on comfort with premium accommodation and a more relaxed budget allocation for travel and food.";
        }

        return "This travel plan is generated based on your budget and trip preferences.";
    }
}