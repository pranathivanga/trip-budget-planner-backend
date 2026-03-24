package com.example.travel_planner.api;

import com.example.travel_planner.domain.itinerary.DayPlan;
import com.example.travel_planner.domain.suggestion.Suggestion;
import com.example.travel_planner.dto.request.BudgetAdjustRequest;
import com.example.travel_planner.exception.TripPlanningException;
import com.example.travel_planner.budget.Budget;
import com.example.travel_planner.budget.BudgetAllocator;
import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.ItineraryGenerator;
import com.example.travel_planner.itinerary.ItineraryService;
import com.example.travel_planner.pdf.PdfService;
import com.example.travel_planner.scoring.PlanScoringService;
import com.example.travel_planner.suggestion.SuggestionService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/trips")
public class TripPlannerController {

    private final ItineraryGenerator generator = new ItineraryGenerator();
    private final SuggestionService suggestionService = new SuggestionService();
    private final PlanScoringService scoringService = new PlanScoringService();
    private final ItineraryService itineraryService = new ItineraryService();
    private final PdfService pdfService = new PdfService();

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @PostMapping("/adjust-budget")
    public TripPlanResponse adjustBudget(@RequestBody BudgetAdjustRequest request) {

        double totalBudget = request.getTotalBudget();

        double travelPercent = request.getTravelPercent();
        double stayPercent = request.getStayPercent();
        double foodPercent = request.getFoodPercent();

// Handle both cases: 0–1 or 0–100
        if (travelPercent <= 1 && stayPercent <= 1 && foodPercent <= 1) {
            travelPercent *= 100;
            stayPercent *= 100;
            foodPercent *= 100;
        }

        double travelCost = totalBudget * (travelPercent / 100.0);
        double stayCost = totalBudget * (stayPercent / 100.0);
        double foodCost = totalBudget * (foodPercent / 100.0);

        double total = travelCost + stayCost + foodCost;

        return new TripPlanResponse(
                "CUSTOM",
                travelCost,
                stayCost,
                foodCost,
                total,
                "CUSTOM",
                "Custom budget allocation based on your preferences."
        );
    }

    @PostMapping("/pdf")
    public ResponseEntity<byte[]> downloadPdf(@RequestBody TripRequest request) {

        Trip trip = new Trip(
                new Location(request.getSourceCity()),
                new Location(request.getDestinationCity()),
                request.getDays(),
                request.getTravelers(),
                StayPreference.STANDARD
        );

        BudgetAllocator allocator = new BudgetAllocator();

        Budget budget = allocator.allocate(
                request.getTotalBudget(),
                trip
        );

        List<TripPlan> allPlans = generator.generatePlans(trip, budget);

        List<TripPlan> plans = allPlans.stream()
                .filter(p -> p.getPlanType().name().equals(request.getPlanType()))
                .collect(Collectors.toList());

        List<DayPlan> itinerary = itineraryService.generateItinerary(trip);

        byte[] pdf = pdfService.generatePdf(plans, itinerary);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tripwise-plan.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PostMapping("/itinerary")
    public List<DayPlan> generateItinerary(@RequestBody TripRequest request) {

        Trip trip = new Trip(
                new Location(request.getSourceCity()),
                new Location(request.getDestinationCity()),
                request.getDays(),
                request.getTravelers(),
                StayPreference.STANDARD
        );

        return itineraryService.generateItinerary(trip);
    }

    @PostMapping("/plan")
    public TripPlannerResponse generatePlans(@RequestBody TripRequest request) {

        if(request.getTotalBudget() < 1000) {
            throw new TripPlanningException(
                    "Budget too low for this trip",
                    "Increase budget or reduce trip days"
            );
        }

        Trip trip = new Trip(
                new Location(request.getSourceCity()),
                new Location(request.getDestinationCity()),
                request.getDays(),
                request.getTravelers(),
                StayPreference.STANDARD
        );

        BudgetAllocator allocator = new BudgetAllocator();

        Budget budget = allocator.allocate(
                request.getTotalBudget(),
                trip
        );

        List<TripPlan> plans = generator.generatePlans(trip, budget);

        TripPlan bestPlan = scoringService.chooseBestPlan(plans);

        List<Suggestion> suggestions =
                suggestionService.generateSuggestions(plans, request.getTotalBudget());

        List<TripPlanResponse> planResponses =
                plans.stream()
                        .map(p -> new TripPlanResponse(
                                p.getPlanType().name(),
                                round(p.getTravelCost().getAmount()),
                                round(p.getStayCost().getAmount()),
                                round(p.getFoodCost().getAmount()),
                                round(p.getTotalCost().getAmount()),
                                p.getBudgetState().name(),
                                p.getExplanation()
                        ))
                        .collect(Collectors.toList());

        List<String> suggestionMessages =
                suggestions.stream()
                        .map(Suggestion::getMessage)
                        .distinct()
                        .collect(Collectors.toList());

        return new TripPlannerResponse(
                planResponses,
                suggestionMessages,
                bestPlan.getPlanType().name()
        );
    }

    @PostMapping("/minimum-budget")
    public MinimumBudgetResponse calculateMinimumBudget(@RequestBody TripRequest request) {

        Trip trip = new Trip(
                new Location(request.getSourceCity()),
                new Location(request.getDestinationCity()),
                request.getDays(),
                request.getTravelers(),
                StayPreference.BUDGET
        );

        BudgetAllocator allocator = new BudgetAllocator();

        Budget dummyBudget = allocator.allocate(1000000, trip);

        List<TripPlan> plans = generator.generatePlans(trip, dummyBudget);

        TripPlan cheapestPlan = plans.stream()
                .min((p1, p2) -> Double.compare(
                        p1.getTotalCost().getAmount(),
                        p2.getTotalCost().getAmount()
                ))
                .orElse(null);

        double travelCost = cheapestPlan.getTravelCost().getAmount();
        double stayCost = cheapestPlan.getStayCost().getAmount();
        double foodCost = cheapestPlan.getFoodCost().getAmount();

        double minimumCost =
                travelCost + stayCost + foodCost;

        minimumCost = Math.round(minimumCost * 100.0) / 100.0;

        return new MinimumBudgetResponse(
                minimumCost,
                travelCost,
                stayCost,
                foodCost,
                request.getDays(),
                "This trip requires at least ₹" + minimumCost + " to be feasible."
        );
    }
}