package com.example.bibliotech;

import com.example.bibliotech.medium.IMedium;

import java.util.ArrayList;
import java.util.List;

public class Bibliotheksverwaltung {
    private List<IMedium> medienBestand;

    public Bibliotheksverwaltung() {
        this.medienBestand = new ArrayList<>();
    }

    public void mediumHinzufuegen(IMedium medium) {
        medienBestand.add(medium);
    }

    public void mediumEntfernen(IMedium medium) {
        medienBestand.remove(medium);
    }

    public List<IMedium> getAlleMedien() {
        return new ArrayList<>(medienBestand);
    }


}