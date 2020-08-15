package com.project.Svalbard.Util;

public class CustomStrCmp {
    public static boolean compare(String a, String b) {
        return a.replace("\\s", "").toLowerCase().replace("_", "")
                .equals(b.replace("\\s", "").toLowerCase().replace("_", ""));
    }
}
