package com.investing_app.service;

import com.investing_app.customexceptions.IncorrectDataType;
import com.investing_app.customexceptions.NullValue;
import com.investing_app.customexceptions.TooManyChar;
import com.investing_app.customexceptions.ValueTooLarge;
import com.investing_app.dao.PitchDAO;
import com.investing_app.entities.Pitch;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

public class PitchServiceImplemented implements PitchService{

    // declare pitch field
    PitchDAO pitchDAO;
    DecimalFormat numberFormat = new DecimalFormat("##.##");

    public PitchServiceImplemented (PitchDAO pitchDAO) {
        this.pitchDAO = pitchDAO;
    }

    @Override
    public Pitch createPitchService(Pitch pitch) {
        Double percentage = this.pitchDAO.createPitch(pitch).getPercentage();
        Pitch _pitch = this.pitchDAO.createPitch(pitch);

        // Try to fix character length of percentage
        if ((pitch.getCreationDate().length() > 10) || (pitch.getPitch().length() > 200) ||
                numberFormat.format(percentage).length() > 5)
            throw new TooManyChar("You are exceeding the value length!");
        if ((pitch.getCreationDate().length() == 0) || (pitch.getPitch().length() == 0))
            throw new NullValue("You must enter a value!");
        if (!Pattern.matches("^[0-9-/]*$", pitch.getCreationDate()))
            throw new IncorrectDataType("Input type not allowed!");
        if (pitch.getAmount() > 1_000_000)
            throw new ValueTooLarge("Please enter an amount below one million dollars.");
        if (pitch.getPercentage() > 100.00)
            throw new ValueTooLarge("Please enter a percentage less than or equal to 100.");
        return _pitch;
    }


    @Override
    public List<Pitch> viewPitchesService() {
        return this.pitchDAO.viewPitches();
    }

    @Override
    public boolean makeOfferService(int pitchId, double amount, double percentage) {
        return this.pitchDAO.makeOffer(pitchId, amount, percentage);
    }

    @Override
    public boolean acceptOfferService(int pitchId) {
        return this.pitchDAO.acceptOffer(pitchId);
    }
}
