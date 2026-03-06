package com.example.travel_planner.api;

import com.example.travel_planner.domain.itinerary.DayPlan;
import com.example.travel_planner.domain.suggestion.Suggestion;
import com.example.travel_planner.service.budget.Budget;
import com.example.travel_planner.service.budget.BudgetAllocator;
import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.trip.Location;
import com.example.travel_planner.domain.trip.StayPreference;
import com.example.travel_planner.domain.trip.Trip;
import com.example.travel_planner.service.ItineraryGenerator;
import com.example.travel_planner.service.itinerary.ItineraryService;
import com.example.travel_planner.service.pdf.PdfService;
import com.example.travel_planner.service.scoring.PlanScoringService;
import com.example.travel_planner.service.suggestion.SuggestionService;

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

        List<TripPlan> plans = generator.generatePlans(trip, budget);

        List<DayPlan> itinerary =
                itineraryService.generateItinerary(trip);

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
                suggestionService.generateSuggestions(plans);

        List<TripPlanResponse> planResponses =
                plans.stream()
                        .map(p -> new TripPlanResponse(
                                p.getPlanType().name(),
                                p.getTravelCost().getAmount(),
                                p.getStayCost().getAmount(),
                                p.getFoodCost().getAmount(),
                                p.getTotalCost().getAmount(),
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
}