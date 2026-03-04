package com.example.travel_planner.domain.plan;

import com.example.travel_planner.domain.cost.Money;
import com.example.travel_planner.domain.decision.FeasibilityResult;

public class TripPlan {

    private final PlanType type;
    private final Money totalCost;
    private final FeasibilityResult feasibility;
    private final String explanation;

    public TripPlan(PlanType type,
                    Money totalCost,
                    FeasibilityResult feasibility,
                    String explanation) {
        this.type = type;
        this.totalCost = totalCost;
        this.feasibility = feasibility;
        this.explanation = explanation;
    }

    public PlanType getType() {
        return type;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public FeasibilityResult getFeasibility() {
        return feasibility;
    }

    public String getExplanation() {
        return explanation;
    }
}