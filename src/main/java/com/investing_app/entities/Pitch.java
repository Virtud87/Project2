package com.investing_app.entities;

import java.util.Objects;

public class Pitch {
    private int pitchId;
    private int businessId;
    private String businessName;
    private String creationDate;
    private String pitch;
    private double amount;
    private double percentage;
    private String status;

    public Pitch() {}

    public Pitch(int pitchId, int businessId, String businessName, String creationDate, String pitch,
                 double amount, double percentage, String status) {
        this.pitchId = pitchId;
        this.businessId = businessId;
        this.businessName = businessName;
        this.creationDate = creationDate;
        this.pitch = pitch;
        this.amount = amount;
        this.percentage = percentage;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pitch{" +
                "pitchId=" + pitchId +
                ", businessId=" + businessId +
                ", businessName='" + businessName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", pitch='" + pitch + '\'' +
                ", amount=" + amount +
                ", percentage=" + percentage +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch1 = (Pitch) o;
        return pitchId == pitch1.pitchId && businessId == pitch1.businessId && Double.compare(pitch1.amount, amount) == 0 && Double.compare(pitch1.percentage, percentage) == 0 && Objects.equals(businessName, pitch1.businessName) && Objects.equals(creationDate, pitch1.creationDate) && Objects.equals(pitch, pitch1.pitch) && Objects.equals(status, pitch1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitchId, businessId, businessName, creationDate, pitch, amount, percentage, status);
    }

    public int getPitchId() {
        return pitchId;
    }

    public void setPitchId(int pitchId) {
        this.pitchId = pitchId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}