package com.example.travel_planner.service;

import com.example.travel_planner.domain.decision.RuleViolation;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {
    public List<RuleViolation> evaluate(List<Rule> rules) {

        List<RuleViolation> violations = new ArrayList<>();

        for (Rule rule : rules) {
            rule.check().ifPresent(violations::add);
        }

        return violations;
    }
}
