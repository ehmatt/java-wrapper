package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.CustomField;
import com.onepagecrm.models.internal.CustomFieldValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class CustomFieldFabricator extends BaseFabricator {

    public static CustomField single() {
        return singleLineText();
    }

    public static List<CustomField> list() {
        List<CustomField> customFields = new ArrayList<>();
        customFields.add(singleLineText());
        customFields.add(multiLineText());
        customFields.add(anniversary());
        customFields.add(date());
        customFields.add(number());
        customFields.add(dropdown());
        customFields.add(multipleChoice());
        return customFields;
    }

    public static CustomField singleLineText() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("Example single line value");
        return new CustomField()
                .setId("561b89cc9007ba41ef000005")
                .setName("TestSingleLine")
                .setPosition(0)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_SINGLE_LINE_TEXT)
                .setValue(value);
    }

    public static CustomField multiLineText() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("Example multi-line value");
        return new CustomField()
                .setId("561b8a209007ba41ef00000b")
                .setName("TestMultiLine")
                .setPosition(1)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_MULTI_LINE_TEXT)
                .setValue(value);
    }

    public static CustomField anniversary() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("2016-01-19");
        return new CustomField()
                .setId("561b8a209007ba41ef00000c")
                .setName("TestAnniversary")
                .setPosition(2)
                .setReminderDays(2)
                .setType(CustomField.TYPE_ANNIVERSARY)
                .setValue(value);
    }

    public static CustomField date() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("2016-01-20");
        return new CustomField()
                .setId("561b8a209007ba41ef00000d")
                .setName("TestDate")
                .setPosition(3)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_DATE)
                .setValue(value);
    }

    public static CustomField number() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue(3.14);
        return new CustomField()
                .setId("561b8a5a9007ba41ef000013")
                .setName("TestNumber")
                .setPosition(4)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_NUMBER)
                .setValue(value);
    }

    public static CustomField dropdown() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("First");
        return new CustomField()
                .setChoices(choicesList())
                .setId("561b8a5a9007ba41ef000014")
                .setName("TestDropdown")
                .setPosition(5)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_DROPDOWN)
                .setValue(value);
    }

    public static CustomField multipleChoice() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue(choicesArray());
        return new CustomField()
                .setChoices(choicesList())
                .setId("561b8a5a9007ba41ef000015")
                .setName("TestMultipleChoice")
                .setPosition(6)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_MULTIPLE_CHOICE)
                .setValue(value);
    }

    public static List<String> choicesList() {
        List<String> choices = new ArrayList<>();
        choices.add("First");
        choices.add("Second");
        choices.add("Third");
        return choices;
    }

    public static String[] choicesArray() {
        return new String[]{"First", "Second", "Third"};
    }
}
