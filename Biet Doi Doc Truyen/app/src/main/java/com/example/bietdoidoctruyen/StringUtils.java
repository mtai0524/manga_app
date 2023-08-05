package com.example.bietdoidoctruyen;

import java.text.Normalizer;

public class StringUtils {
    public static String removeDiacritics(String str) {
        if (str == null) {
            return null;
        }

        String result = Normalizer.normalize(str, Normalizer.Form.NFD);
        result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return result;
    }
}
