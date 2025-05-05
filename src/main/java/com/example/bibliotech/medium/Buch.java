package com.example.bibliotech.medium;

public class Buch extends Medium {

    public Buch(String name, int erscheinungsjahr, String autor) {
        super(name, erscheinungsjahr, autor);
    }

    @Override
    public String getTyp() {
        return "Buch";
    }
}

