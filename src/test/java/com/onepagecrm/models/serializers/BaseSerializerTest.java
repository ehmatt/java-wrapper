package com.onepagecrm.models.serializers;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.serializer.BaseSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 07/12/2015.
 */
public class BaseSerializerTest extends BaseTest {

    private List<String> stringList;
    private String[] stringArray;
    private String expectedString;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");

        stringArray = new String[stringList.size()];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";

        expectedString = "one__,__two__,__three";
    }

    public void testEncodeParams_NullMap() {
        String expected = "";
        String actual = BaseSerializer.encodeParams(null);

        assertEquals("Method mis-calculated query String based off of params",
                expected, actual);
    }

    public void testEncodeParams_Correct() {
        Map<String, String> params = new HashMap<>();
        params.put("per_page", "10");
        params.put("page", "1");

        String expected = "per_page=10&page=1";
        String actual = BaseSerializer.encodeParams(params);

        assertEquals("Method mis-calculated query String based off of params",
                expected, actual);
    }

    public void testToCommaSeparatedString_FromList_Null() {
        List<String> strings = null;

        String expected = "";
        String actual = BaseSerializer.toCommaSeparatedString(strings);

        assertEquals("List of strings not converted to string correctly",
                expected, actual);
    }

    public void testToCommaSeparatedString_FromList_Correct() {
        String expected = expectedString;
        String actual = BaseSerializer.toCommaSeparatedString(stringList);

        assertEquals("List of strings not converted to string correctly",
                expected, actual);
    }

    public void testToCommaSeparatedString_FromArray_Null() {
        String[] array = null;

        String expected = "";
        String actual = BaseSerializer.toCommaSeparatedString(array);

        assertEquals("Array of strings not converted to string correctly",
                expected, actual);
    }

    public void testToCommaSeparatedString_FromArray_Correct() {
        String expected = expectedString;
        String actual = BaseSerializer.toCommaSeparatedString(stringArray);

        assertEquals("Array of strings not converted to string correctly",
                expected, actual);
    }

    public void testToListOfStrings_Null() {
        String joinedString = null;

        List<String> expected = new ArrayList<>();
        List<String> actual = BaseSerializer.toListOfStrings(joinedString);

        assertEquals("Strings not converted to list of strings correctly",
                expected, actual);
    }

    public void testToListOfStrings_Correct() {
        String joinedString = expectedString;

        List<String> expected = stringList;
        List<String> actual = BaseSerializer.toListOfStrings(joinedString);

        assertEquals("String not converted to list of strings correctly",
                expected, actual);
    }

    @SuppressWarnings({"MismatchedReadAndWriteOfArray", "ConstantConditions"})
    public void testToArrayOfStrings_Null() {
        String joinedString = null;

        String[] expected = new String[3];
        String[] actual = BaseSerializer.toArrayOfStrings(joinedString);

        assertEquals("String not converted to array of strings correctly",
                expected.length, 3);
        assertEquals("String not converted to array of strings correctly",
                actual.length, 128);
        assertEquals("String not converted to array of strings correctly",
                expected[0], actual[0]);
        assertEquals("String not converted to array of strings correctly",
                expected[1], actual[1]);
        assertEquals("String not converted to array of strings correctly",
                expected[2], actual[2]);
    }

    public void testToArrayOfStrings_Correct() {
        String joinedString = expectedString;

        String[] expected = stringArray;
        String[] actual = BaseSerializer.toArrayOfStrings(joinedString);

        assertEquals("String not converted to array of strings correctly",
                expected.length, 3);
        assertEquals("String not converted to array of strings correctly",
                actual.length, 3);
        assertEquals("String not converted to array of strings correctly",
                expected[0], actual[0]);
        assertEquals("String not converted to array of strings correctly",
                expected[1], actual[1]);
        assertEquals("String not converted to array of strings correctly",
                expected[2], actual[2]);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
