package com.example.travel_planner.service.scoring;

import com.example.travel_planner.domain.plan.TripPlan;

import java.util.Comparator;
import java.util.List;

public class PlanScoringService {

    public TripPlan chooseBestPlan(List<TripPlan> plans) {

        return plans.stream()
                .max(Comparator.comparingInt(this::score))
                .orElse(null);
    }

    private int score(TripPlan plan) {

        int score = 0;

        switch (plan.getBudgetState()) {

            case LUXURY_POSSIBLE:
                score += 30;
                break;

            case COMFORTABLE:
                score += 20;
                break;

            case TIGHT:
                score += 10;
                break;

            case NOT_FEASIBLE:
                score -= 20;
                break;
        }

        double stay = plan.getStayCost().getAmount();
        double total = plan.getTotalCost().getAmount();

        double comfortRatio = stay / total;

        if (comfortRatio > 0.6) {
            score += 10;
        }

        return score;
    }
}