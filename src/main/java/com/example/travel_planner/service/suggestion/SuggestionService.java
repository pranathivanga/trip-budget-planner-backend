package com.example.travel_planner.service.suggestion;

import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.suggestion.Suggestion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuggestionService {

    public List<Suggestion> generateSuggestions(List<TripPlan> plans) {

        Set<Suggestion> suggestions = new HashSet<>();

        for (TripPlan plan : plans) {

            if (plan.getBudgetState().name().equals("NOT_FEASIBLE")) {
                suggestions.add(
                        new Suggestion(
                                "Reduce trip days or downgrade stay preference to fit the budget."
                        )
                );
            }

            if (plan.getBudgetState().name().equals("TIGHT")) {
                suggestions.add(
                        new Suggestion(
                                "Consider reducing food or accommodation spending slightly."
                        )
                );
            }

            if (plan.getBudgetState().name().equals("LUXURY_POSSIBLE")) {
                suggestions.add(
                        new Suggestion(
                                "Your budget comfortably supports this trip with luxury options available."
                        )
                );
            }
        }

        return new ArrayList<>(suggestions);
    }
}