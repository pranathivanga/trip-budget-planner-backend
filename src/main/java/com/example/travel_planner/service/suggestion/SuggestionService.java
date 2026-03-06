package com.example.travel_planner.service.suggestion;

import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.suggestion.Suggestion;
import com.example.travel_planner.domain.decision.BudgetState;

import java.util.*;

public class SuggestionService {

    public List<Suggestion> generateSuggestions(List<TripPlan> plans) {

        Set<String> uniqueMessages = new HashSet<>();

        for (TripPlan plan : plans) {

            if (plan.getBudgetState() == BudgetState.LUXURY_POSSIBLE) {
                uniqueMessages.add(
                        "Your budget comfortably supports this trip with luxury options available."
                );
            }

            if (plan.getBudgetState() == BudgetState.TIGHT) {
                uniqueMessages.add(
                        "Your budget is tight. Consider reducing stay category or shortening the trip."
                );
            }

            uniqueMessages.add(
                    "Consider booking accommodations in advance to lock in better rates across all plan types."
            );
        }

        List<Suggestion> result = new ArrayList<>();

        for (String msg : uniqueMessages) {
            result.add(new Suggestion(msg));
        }

        return result;
    }
}