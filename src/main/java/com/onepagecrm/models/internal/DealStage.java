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

    public DealStage setPercentage(int percentage) {
        this.percentage = percentage;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public DealStage setLabel(String label) {
        this.label = label;
        return this;
    }
}