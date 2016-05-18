package com.onepagecrm.models.internal;

import java.util.LinkedList;
import java.util.List;

public class DealStage {

    public static final String LABEL_WON = "Won";
    public static final String LABEL_LOST = "Lost";

    private Integer percentage;
    private String label;

    public DealStage() {

    }

    public static List<DealStage> addDefaults(LinkedList<DealStage> stages) {
        if (stages != null) {
            stages.add(won());
            stages.add(lost());
        }
        return stages;
    }

    public static DealStage won() {
        return new DealStage().setLabel(LABEL_WON);
    }

    public static DealStage lost() {
        return new DealStage().setLabel(LABEL_LOST);
    }

    public String getFriendlyString() {
        if (percentage != null) {
            return percentage + "%" + ((label == null) ? "" : " - " + label);
        } else {
            return label;
        }
    }

    public Integer getPercentage() {
        return percentage;
    }

    public DealStage setPercentage(Integer percentage) {
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