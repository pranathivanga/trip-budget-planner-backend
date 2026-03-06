package com.example.travel_planner.domain.score;

public class PlanScore {

    private final int comfort;
    private final int safety;
    private final int experience;

    public PlanScore(int comfort, int safety, int experience) {
        this.comfort = comfort;
        this.safety = safety;
        this.experience = experience;
    }

    public int getComfort() {
        return comfort;
    }

    public int getSafety() {
        return safety;
    }

    public int getExperience() {
        return experience;
    }
}