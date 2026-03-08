package com.example.travel_planner.service.suggestion;

import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.suggestion.Suggestion;
import com.example.travel_planner.domain.decision.BudgetState;

import java.util.ArrayList;
import java.util.List;

public class SuggestionService {

    public List<Suggestion> generateSuggestions(List<TripPlan> plans, double userBudget) {

        List<Suggestion> suggestions = new ArrayList<>();

        double minimumRequired =
                plans.stream()
                        .mapToDouble(p -> p.getTotalCost().getAmount())
                        .min()
                        .orElse(0);

        if (userBudget < minimumRequired) {

            double difference = minimumRequired - userBudget;

            suggestions.add(
                    new Suggestion(
                            "Trip requires at least ₹" + minimumRequired +
                                    ". Your budget is ₹" + userBudget
                    )
            );

            suggestions.add(
                    new Suggestion(
                            "Increase your budget by about ₹" + difference
                    )
            );

            suggestions.add(
                    new Suggestion(
                            "Reduce trip duration or switch to budget accommodation."
                    )
            );

            return suggestions;
        }

        for (TripPlan plan : plans) {

            if (plan.getBudgetState() == BudgetState.LUXURY_POSSIBLE) {

                suggestions.add(
                        new Suggestion(
                                "Your budget comfortably supports this trip with luxury options available."
                        )
                );

                break;
            }
        }

        suggestions.add(
                new Suggestion(
                        "Consider booking accommodations in advance to lock in better rates."
                )
        );

        return suggestions;
    }
}