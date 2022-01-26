package com.investing_app.entities;

import java.util.Objects;

public class Commenting {

    private int commentId;
    private int businessId;
    private int pitchId;
    private int sharkId;
    private String commenting;
    private String createDate;

    public Commenting(){}

    public Commenting(int commentId, int businessId, int pitchId, int sharkId, String commenting, String createDate) {
        this.commentId = commentId;
        this.businessId = businessId;
        this.pitchId = pitchId;
        this.sharkId = sharkId;
        this.commenting = commenting;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Business{" +
                "commentId=" + commentId +
                "businessId=" + businessId +
                "pitchId=" + pitchId +
                "sharkId=" + sharkId +
                ", commenting='" + commenting + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commenting that = (Commenting) o;
        return commentId == that.commentId && businessId == that.businessId && pitchId == that.pitchId && sharkId == that.sharkId && Objects.equals(commenting, that.commenting) && Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, businessId, pitchId, sharkId, commenting, createDate);
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getPitchId() {
        return pitchId;
    }

    public void setPitchId(int pitchId) {
        this.pitchId = pitchId;
    }

    public int getSharkId() {
        return sharkId;
    }

    public void setSharkId(int sharkId) {
        this.sharkId = sharkId;
    }

    public String getCommenting() {
        return commenting;
    }

    public void setCommenting(String commenting) {
        this.commenting = commenting;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
