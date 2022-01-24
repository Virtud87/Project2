package com.investing_app.entities;

import java.util.Objects;

public class Offer {
    int pitchId;
    double amount;
    double percentage;

    public Offer() {}

    public Offer(int pitchId, double amount, double percentage) {
        this.pitchId = pitchId;
        this.amount = amount;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "pitchId=" + pitchId +
                ", amount=" + amount +
                ", percentage=" + percentage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return pitchId == offer.pitchId && Double.compare(offer.amount, amount) == 0 && Double.compare(offer.percentage, percentage) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitchId, amount, percentage);
    }

    public int getPitchId() {
        return pitchId;
    }

    public void setPitchId(int pitchId) {
        this.pitchId = pitchId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
