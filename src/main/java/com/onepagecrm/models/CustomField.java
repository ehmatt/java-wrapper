package com.onepagecrm.models;

import com.onepagecrm.models.serializer.CustomFieldSerializer;
import com.onepagecrm.net.ApiResource;

import java.io.Serializable;
import java.util.List;

public class CustomField extends ApiResource implements Serializable {

    private String id;
    private String name;
    private int position;
    private String type;
    private List<String> choices;
    private int reminderDays;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public CustomField setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return CustomFieldSerializer.toJsonObject(this);
    }

    public String getName() {
        return name;
    }

    public CustomField setName(String name) {
        this.name = name;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public CustomField setPosition(int position) {
        this.position = position;
        return this;
    }

    public String getType() {
        return type;
    }

    public CustomField setType(String type) {
        this.type = type;
        return this;
    }

    public List<String> getChoices() {
        return choices;
    }

    public CustomField setChoices(List<String> choices) {
        this.choices = choices;
        return this;
    }

    public int getReminderDays() {
        return reminderDays;
    }

    public CustomField setReminderDays(int reminderDays) {
        this.reminderDays = reminderDays;
        return this;
    }
}
