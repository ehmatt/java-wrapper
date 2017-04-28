package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.CustomField;
import com.onepagecrm.models.internal.CustomFieldValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class CompanyFieldFabricator extends BaseFabricator {

    public static CustomField single() {
        return singleLineText();
    }

    public static List<CustomField> list() {
        List<CustomField> customFields = new ArrayList<>();
        customFields.add(singleLineText());
        customFields.add(multiLineText());
        customFields.add(date());
        customFields.add(number());
        customFields.add(dropdown());
        customFields.add(multipleChoice());
        return customFields;
    }

    public static CustomField singleLineText() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("Example Company single-line value");
        return new CustomField()
                .setId("578bbeb79007ba5344fb68eb")
                .setName("CompanySingleLine")
                .setPosition(0)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_SINGLE_LINE_TEXT)
                .setCfType(CustomField.CF_TYPE_COMPANY)
                .setValue(value);
    }

    public static CustomField multiLineText() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("Example Company multi-line value");
        return new CustomField()
                .setId("578bbf109007ba5344fb68ec")
                .setName("CompanyMultiLine")
                .setPosition(1)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_MULTI_LINE_TEXT)
                .setCfType(CustomField.CF_TYPE_COMPANY)
                .setValue(value);
    }

    public static CustomField date() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("2016-01-21");
        return new CustomField()
                .setId("578bbf1e9007ba5344fb68ed")
                .setName("CompanyDate")
                .setPosition(2)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_DATE)
                .setCfType(CustomField.CF_TYPE_COMPANY)
                .setValue(value);
    }

    public static CustomField number() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue(3.14);
        return new CustomField()
                .setId("578bbf259007ba5344fb68ee")
                .setName("CompanyNumber")
                .setPosition(3)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_NUMBER)
                .setCfType(CustomField.CF_TYPE_COMPANY)
                .setValue(value);
    }

    public static CustomField dropdown() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue("First");
        return new CustomField()
                .setChoices(choicesList())
                .setId("578bbf389007ba5344fb68ef")
                .setName("CompanyDropdown")
                .setPosition(4)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_DROPDOWN)
                .setCfType(CustomField.CF_TYPE_COMPANY)
                .setValue(value);
    }

    public static CustomField multipleChoice() {
        CustomFieldValue value = new CustomFieldValue();
        value.setValue(choicesArray());
        return new CustomField()
                .setChoices(choicesList())
                .setId("578bbf4f9007ba5344fb68f0")
                .setName("CompanyMultipleChoice")
                .setPosition(5)
                .setReminderDays(-1)
                .setType(CustomField.TYPE_MULTIPLE_CHOICE)
                .setCfType(CustomField.CF_TYPE_COMPANY)
                .setValue(value);
    }

    public static List<String> choicesList() {
        return CustomFieldFabricator.choicesList();
    }

    public static String[] choicesArray() {
        return CustomFieldFabricator.choicesArray();
    }
}
