package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.CustomField;
import com.onepagecrm.models.internal.CustomFieldValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 28/06/2017.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class DealFieldFabricator extends BaseFabricator {

    public static CustomField single() {
        return singleLineText();
    }

    public static List<CustomField> list() {
        List<CustomField> customFields = new ArrayList<>();
        customFields.add(singleLineText());
        customFields.add(multiLineText());
        customFields.add(number());
        customFields.add(dropdown());
        customFields.add(date());
        customFields.add(multipleChoice());
        return customFields;
    }

    public static CustomField singleLineText() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("Example Deal single-line value");
        return new CustomField()
                .setId("5947a6279007ba40b8ecbb0a")
                .setName("DealSingleLine")
                .setPosition(0)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_SINGLE_LINE_TEXT)
                .setCfType(CustomField.CF_TYPE_DEAL)
                .setValue(value);
    }

    public static CustomField multiLineText() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("Example Deal multi-line value");
        return new CustomField()
                .setId("5947a6479007ba40b8ecbb0b")
                .setName("DealMultiLine")
                .setPosition(1)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_MULTI_LINE_TEXT)
                .setCfType(CustomField.CF_TYPE_DEAL)
                .setValue(value);
    }

    public static CustomField number() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue(3.14);
        return new CustomField()
                .setId("5947a6569007ba40b8ecbb0c")
                .setName("DealNumber")
                .setPosition(2)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_NUMBER)
                .setCfType(CustomField.CF_TYPE_DEAL)
                .setValue(value);
    }

    public static CustomField dropdown() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("First");
        return new CustomField()
                .setChoices(choicesList())
                .setId("5947a6749007ba40b8ecbb0d")
                .setName("DealDropdown")
                .setPosition(3)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_DROPDOWN)
                .setCfType(CustomField.CF_TYPE_DEAL)
                .setValue(value);
    }

    public static CustomField date() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("2016-01-20");
        return new CustomField()
                .setId("5947a6849007ba40b8ecbb11")
                .setName("DealDate")
                .setPosition(4)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_DATE)
                .setCfType(CustomField.CF_TYPE_DEAL)
                .setValue(value);
    }

    public static CustomField multipleChoice() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue(choicesArray());
        return new CustomField()
                .setChoices(choicesList())
                .setId("5947a6aa9007ba40b8ecbb12")
                .setName("DealMultipleChoice")
                .setPosition(5)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_MULTIPLE_CHOICE)
                .setCfType(CustomField.CF_TYPE_DEAL)
                .setValue(value);
    }

    public static List<String> choicesList() {
        return CustomFieldFabricator.choicesList();
    }

    public static String[] choicesArray() {
        return CustomFieldFabricator.choicesArray();
    }
}
