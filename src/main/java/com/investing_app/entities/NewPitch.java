package com.investing_app.entities;

import java.util.Objects;

public class NewPitch {
    private int businessId;
    private String creationDate;
    private String pitch;
    private double amount;
    private double percentage;

    public NewPitch() {}

    public NewPitch(int businessId, String creationDate, String pitch, double amount, double percentage) {
        this.businessId = businessId;
        this.creationDate = creationDate;
        this.pitch = pitch;
        this.amount = amount;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "NewPitch{" +
                "businessId=" + businessId +
                ", creationDate='" + creationDate + '\'' +
                ", pitch='" + pitch + '\'' +
                ", amount=" + amount +
                ", percentage=" + percentage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewPitch newPitch = (NewPitch) o;
        return businessId == newPitch.businessId && Double.compare(newPitch.amount, amount) == 0 && Double.compare(newPitch.percentage, percentage) == 0 && Objects.equals(creationDate, newPitch.creationDate) && Objects.equals(pitch, newPitch.pitch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessId, creationDate, pitch, amount, percentage);
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
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
