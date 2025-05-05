package com.example.bibliotech.medium;

public class DVD extends Medium {

    public DVD(String name, int erscheinungsjahr, String autor) {
        super(name, erscheinungsjahr, autor);
    }

    @Override
    public String getTyp() {
        return "DVD";
    }
}

