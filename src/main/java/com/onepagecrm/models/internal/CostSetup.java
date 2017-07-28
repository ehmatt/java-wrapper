package com.onepagecrm.models.internal;

import java.io.Serializable;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 19/06/2017.
 */
public class CostSetup implements Serializable {

    private Boolean enabled;
    private Boolean required;
    private Commission.Base commissionBase;
    private Double commissionPercentage;

    public Boolean getCostEnabled() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled != null && enabled;
    }

    public CostSetup setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Boolean getCostRequired() {
        return required;
    }

    public boolean isRequired() {
        return required != null && required;
    }

    public CostSetup setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public Commission.Base getCommissionBase() {
        return commissionBase;
    }

    public CostSetup setCommissionBase(Commission.Base commissionBase) {
        this.commissionBase = commissionBase;
        return this;
    }

    public Double getCommissionPercentage() {
        return commissionPercentage;
    }

    public CostSetup setCommissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
        return this;
    }

    @Override
    public String toString() {
        return "CostSetup{" +
                "enabled=" + enabled +
                ", required=" + required +
                ", commissionBase='" + commissionBase + '\'' +
                ", commissionPercentage='" + commissionPercentage + '\'' +
                '}';
    }
}
