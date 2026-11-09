package com.example.timetravel1;

public class IntentKeys {
    public static final String MATRICULE = "12345"; // ton matricule

    public static String key(String matricule, String name) {
        return matricule + "_" + name;
    }
}