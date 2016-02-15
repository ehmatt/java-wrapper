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
        List<CustomField> lCustomFields = new ArrayList<>();
        lCustomFields.add(singleLineText());
        lCustomFields.add(multiLineText());
        lCustomFields.add(anniversary());
        lCustomFields.add(date());
        lCustomFields.add(number());
        lCustomFields.add(dropdown());
        lCustomFields.add(multipleChoice());
        return lCustomFields;
    }

    public static CustomField singleLineText() {
        CustomFieldValue lValue = new CustomFieldValue();
        lValue.setValue("Example single line value");
        return new CustomField()
                .setId("561b89cc9007ba41ef000005")
                .setName("TestSingleLine")
                .setPosition(0)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_SINGLE_LINE_TEXT)
                .setValue(lValue);
    }

    public static CustomField multiLineText() {
        CustomFieldValue lValue = new CustomFieldValue();
        lValue.setValue("Example multi-line value");
        return new CustomField()
                .setId("561b8a209007ba41ef00000b")
                .setName("TestMultiLine")
                .setPosition(1)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_MULTI_LINE_TEXT)
                .setValue(lValue);
    }

    public static CustomField anniversary() {
        CustomFieldValue lValue = new CustomFieldValue();
        lValue.setValue("2016-01-19");
        return new CustomField()
                .setId("561b8a209007ba41ef00000c")
                .setName("TestAnniversary")
                .setPosition(2)
                .setReminderDays(2)
                .setType(CustomField.TYPE_ANNIVERSARY)
                .setValue(lValue);
    }

    public static CustomField date() {
        CustomFieldValue lValue = new CustomFieldValue();
        lValue.setValue("2016-01-20");
        return new CustomField()
                .setId("561b8a209007ba41ef00000d")
                .setName("TestDate")
                .setPosition(3)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_DATE)
                .setValue(lValue);
    }

    public static CustomField number() {
        CustomFieldValue lValue = new CustomFieldValue();
        lValue.setValue(3.14);
        return new CustomField()
                .setId("561b8a5a9007ba41ef000013")
                .setName("TestNumber")
                .setPosition(4)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_NUMBER)
                .setValue(lValue);
    }

    public static CustomField dropdown() {
        CustomFieldValue lValue = new CustomFieldValue();
        lValue.setValue("First");
        return new CustomField()
                .setChoices(choicesList())
                .setId("561b89cc9007ba41ef000005")
                .setName("TestDropdown")
                .setPosition(5)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_DROPDOWN)
                .setValue(lValue);
    }

    public static CustomField multipleChoice() {
        CustomFieldValue lValue = new CustomFieldValue();
        lValue.setValue(choicesArray());
        return new CustomField()
                .setChoices(choicesList())
                .setId("561b89cc9007ba41ef000005")
                .setName("TestMultipleChoice")
                .setPosition(6)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_MULTIPLE_CHOICE)
                .setValue(lValue);
    }

    public static List<String> choicesList() {
        List<String> lChoices = new ArrayList<>();
        lChoices.add("First");
        lChoices.add("Second");
        lChoices.add("Third");
        return lChoices;
    }

    public static String[] choicesArray() {
        return new String[]{"First", "Second", "Third"};
    }
}
