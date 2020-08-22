package com.project.Svalbard.Util;

import java.util.List;

public class CustomStrCmp {
    public static boolean compare(String a, String b) {
        return a.replace("\\s", "").toLowerCase().replace("_", "")
                .equals(b.replace("\\s", "").toLowerCase().replace("_", ""));
    }

    public static String getequivalent(String a, List<String> b) {
        for (String str : b) {
            if (compare(a, str)) {
                return str;
            }
        }
        return null;
    }
}
