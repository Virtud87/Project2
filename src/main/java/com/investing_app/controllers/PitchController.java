package com.investing_app.controllers;

import com.google.gson.Gson;
import com.investing_app.entities.Offer;
import com.investing_app.entities.Pitch;
import com.investing_app.service.PitchService;
import java.util.List;
import io.javalin.http.Handler;

public class PitchController {

    PitchService pitchService;

    public PitchController(PitchService pitchService) {
        this.pitchService = pitchService;
    }

    public Handler createPitch = ctx -> {
        Gson gson = new Gson();
        Pitch pitch = gson.fromJson(ctx.body(), Pitch.class);
        try {
            Pitch newPitch = this.pitchService.createPitchService(pitch);
            String newPitchJson = gson.toJson(newPitch);
            ctx.result(newPitchJson);
            ctx.status(201);
        }
        catch (Exception e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    };

    public Handler viewPitches = ctx -> {
        List<Pitch> pitches = this.pitchService.viewPitchesService();
        Gson gson = new Gson();
        String pitchesJSONs = gson.toJson(pitches);
        ctx.result(pitchesJSONs);
        ctx.status(200);
    };

    public Handler makeOffer = ctx -> {
        Gson gson = new Gson();
        Offer offerMade = gson.fromJson(ctx.body(), Offer.class);
        try {
            boolean offer = this.pitchService.makeOfferService(offerMade.getPitchId(),
                    offerMade.getAmount(), offerMade.getPercentage());
            ctx.result(String.valueOf(offer));
            ctx.status(200);
        }
        catch (Exception e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }

    };

    public Handler acceptOffer = ctx -> {
        int pitchId = Integer.parseInt(ctx.pathParam("pitchId"));
        boolean offerAccepted = this.pitchService.acceptOfferService(pitchId);
        ctx.result(String.valueOf(offerAccepted));
        ctx.status(200);
    };
}
