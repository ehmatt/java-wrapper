package com.onepagecrm.models.internal;

import java.io.Serializable;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 19/06/2017.
 */
public class Cost implements Serializable {

    /**
     * Constants
     */

    private final static String COST_MARGIN = "margin";
    private final static String COST_AMOUNT = "amount";
    private final static String COST_OTHER = "other"; // Catch all.

    /**
     * Member variables.
     */

    public enum Commission {
        MARGIN(COST_MARGIN),
        AMOUNT(COST_AMOUNT),
        OTHER(COST_OTHER);

        private String cost;

        Commission(String cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return cost;
        }

        public static Commission fromString(String string) {
            if (string == null) return null;
            switch (string) {
                case COST_MARGIN:
                    return MARGIN;
                case COST_AMOUNT:
                    return AMOUNT;
                default:
                    return OTHER;
            }
        }
    }

    private Boolean costEnabled;
    private Boolean costRequired;
    private Commission commissionBase;
    private Double commissionPercentage;

    public Boolean getCostEnabled() {
        return costEnabled;
    }

    public boolean isCostEnabled() {
        return costEnabled != null && costEnabled;
    }

    public Cost setCostEnabled(Boolean costEnabled) {
        this.costEnabled = costEnabled;
        return this;
    }

    public Boolean getCostRequired() {
        return costRequired;
    }

    public boolean isCostRequired() {
        return costRequired != null && costRequired;
    }

    public Cost setCostRequired(Boolean costRequired) {
        this.costRequired = costRequired;
        return this;
    }

    public Commission getCommissionBase() {
        return commissionBase;
    }

    public Cost setCommissionBase(Commission commissionBase) {
        this.commissionBase = commissionBase;
        return this;
    }

    public Double getCommissionPercentage() {
        return commissionPercentage;
    }

    public Cost setCommissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
        return this;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "costEnabled=" + costEnabled +
                ", costRequired=" + costRequired +
                ", commissionBase=" + commissionBase +
                ", commissionPercentage=" + commissionPercentage +
                '}';
    }
}
