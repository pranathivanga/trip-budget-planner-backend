package com.example.travel_planner.score;

import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.score.PlanScore;

public class ScoreService {

    public PlanScore calculateScore(TripPlan plan) {

        int comfort;
        int safety;
        int experience;

        switch (plan.getPlanType()) {

            case BUDGET:
                comfort = 5;
                safety = 9;
                experience = 6;
                break;

            case BALANCED:
                comfort = 7;
                safety = 7;
                experience = 8;
                break;

            case COMFORT:
                comfort = 9;
                safety = 5;
                experience = 9;
                break;

            default:
                comfort = 6;
                safety = 6;
                experience = 6;
        }

        return new PlanScore(comfort, safety, experience);
    }
}