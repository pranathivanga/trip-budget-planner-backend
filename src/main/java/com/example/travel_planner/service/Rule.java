package com.example.travel_planner.service;

import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.Optional;

public interface Rule {
    Optional<RuleViolation> check();
}
