package com.example.travel_planner.api;

import java.util.List;

public class TripPlannerResponse {

    private List<TripPlanResponse> plans;
    private List<String> suggestions;
    private String recommendedPlanType;
    public TripPlannerResponse(List<TripPlanResponse> plans,
                               List<String> suggestions,
                               String recommendedPlanType) {
        this.plans = plans;
        this.suggestions = suggestions;
        this.recommendedPlanType = recommendedPlanType;
    }
    public String getRecommendedPlanType() {
        return recommendedPlanType;
    }
    public List<TripPlanResponse> getPlans() {
        return plans;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}