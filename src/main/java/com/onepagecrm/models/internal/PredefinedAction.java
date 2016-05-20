package com.onepagecrm.models.internal;

import com.onepagecrm.net.ApiResource;

import java.io.Serializable;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 17/05/2016.
 */
public class PredefinedAction extends ApiResource implements Serializable {

    private String id;
    private String text;
    private Integer days;

    public PredefinedAction() {

    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public PredefinedAction setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return null;
    }

    public String getText() {
        return text;
    }

    public PredefinedAction setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getDays() {
        return days;
    }

    public PredefinedAction setDays(Integer days) {
        this.days = days;
        return this;
    }
}
