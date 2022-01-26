package com.investing_app.service;

import com.investing_app.entities.NewPitch;
import com.investing_app.entities.Pitch;

import java.util.List;

public interface PitchService {
    Pitch createPitchService(Pitch pitch);

    List<Pitch> viewPitchesService();

    boolean makeOfferService(int pitchId, double amount, double percentage);

    boolean acceptOfferService(int pitchId);
}
