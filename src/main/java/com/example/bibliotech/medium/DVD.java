package com.example.bibliotech.medium;

public class DVD extends AbstractMedium {
    private int laufzeit;
    private String fsk;

    public DVD(String titel, String autor, int erscheinungsjahr, int laufzeit, String fsk) {
        super(titel, autor, erscheinungsjahr);
        this.laufzeit = laufzeit;
        this.fsk = fsk;
    }

    public int getLaufzeit() {
        return laufzeit;
    }

    public String getFsk() {
        return fsk;
    }
}