package com.example.travel_planner.domain.itinerary;

public class DayPlan {

    private int day;
    private String activity;

    public DayPlan(int day, String activity) {
        this.day = day;
        this.activity = activity;
    }

    public int getDay() {
        return day;
    }

    public String getActivity() {
        return activity;
    }
}