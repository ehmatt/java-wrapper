package com.onepagecrm.models.internal;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 20/06/2016.
 */
public class AlphabeticalIndices {

    public static String[] letters;

    static {
        letters = new String[]{
                //"ALL",
                //"#",
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z"
        };
    }

    public static int[] indexes;

    static {
        indexes = new int[]{
                //0,
                //1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                11,
                12,
                13,
                14,
                15,
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
                25,
                26,
                27
        };
    }

    public static Map<String, String> letterMap;

    static {
        letterMap = new LinkedHashMap<>();
        for (String letter : letters) {
            letterMap.put(letter, letter);
        }
    }

    public static Map<String, Integer> letterToIndexMap;

    static {
        letterToIndexMap = new LinkedHashMap<>();
        letterToIndexMap.put("ALL", 0);
        letterToIndexMap.put("#", 1);
        for (int i = 0; i < letters.length; i++) {
            letterToIndexMap.put(letters[i], indexes[i]);
        }
    }

    public static Map<Integer, String> indexToLetterMap;

    static {
        indexToLetterMap = new LinkedHashMap<>();
        indexToLetterMap.put(0, "ALL");
        indexToLetterMap.put(1, "#");
        for (int i = 0; i < indexes.length; i++) {
            indexToLetterMap.put(indexes[i], letters[i]);
        }
    }

    public static String getIndex(String wordToBeIndexed) {
        if (Utilities.notNullOrEmpty(wordToBeIndexed)) {
            String upperCaseLetter = Utilities.firstCapitalized(wordToBeIndexed);
            if (letterMap.containsKey(upperCaseLetter)) {
                return upperCaseLetter;
            } else {
                return "#";
            }
        }
        return null;
    }
}
