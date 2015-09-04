package com.onepagecrm.models;

import java.util.List;

import com.onepagecrm.net.ApiResource;

public class CustomField extends ApiResource {

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
        String retString;

        retString = "CustomField{" +
                "id=\'" + id + "\', " +
                "name=\'" + name + "\', " +
                "type=\'" + type + "\', ";

        if (choices != null && !choices.isEmpty()) {
            retString += ", Choices{";
            for (int i = 0; i < choices.size(); i++) {
                if (i == (choices.size() - 1)) {
                    retString += "\'" + choices.get(i) + "\'";
                } else {
                    retString += "\'" + choices.get(i) + "\', ";
                }
            }
            retString += "}, ";
        }

        retString += "position=\'" + position + "\'";

        // private [ ] reminderDays ???

        retString += "}";
        return retString;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public int getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(int reminderDays) {
        this.reminderDays = reminderDays;
    }
}
