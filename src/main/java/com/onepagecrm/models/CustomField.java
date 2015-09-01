package com.onepagecrm.models;

import java.util.List;

import com.onepagecrm.net.ApiResource;

public class CustomField extends ApiResource {

    private String id;
    private String name;
    private String type;
    private List<String> choices;
    private int position;

    // private [ ] reminderDays ???

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

    @Override
    public String getId() {
        return this.id;
    }
}
