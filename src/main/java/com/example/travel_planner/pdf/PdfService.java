
package com.example.travel_planner.pdf;

import com.example.travel_planner.domain.plan.TripPlan;
import com.example.travel_planner.domain.itinerary.DayPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfService {

    public byte[] generatePdf(List<TripPlan> plans, List<DayPlan> itinerary) {

        try {

            Document document = new Document();

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("TripWise Travel Plan"));
            document.add(new Paragraph(" "));

            for (TripPlan plan : plans) {

                document.add(new Paragraph("Plan: " + plan.getPlanType()));

                document.add(new Paragraph("Travel Cost: ₹" + plan.getTravelCost().getAmount()));
                document.add(new Paragraph("Stay Cost: ₹" + plan.getStayCost().getAmount()));
                document.add(new Paragraph("Food Cost: ₹" + plan.getFoodCost().getAmount()));
                document.add(new Paragraph("Total Cost: ₹" + plan.getTotalCost().getAmount()));

                document.add(new Paragraph(" "));
            }

            document.add(new Paragraph("Daily Itinerary"));

            for (DayPlan day : itinerary) {

                document.add(new Paragraph(
                        "Day " + day.getDay() + ": " + day.getActivity()
                ));
            }

            document.close();

            return out.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}