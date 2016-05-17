package com.onepagecrm.models.internal;

public class DealStage {

    private int percentage;
    private String label;

    public DealStage() {

    }

    public String getFriendlyString() {
        return percentage + "%" + ((label == null) ? "" : " - " + label);
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}