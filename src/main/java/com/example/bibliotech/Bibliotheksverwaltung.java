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

    public List<IMedium> sucheNachTitel(String titel) {
        List<IMedium> gefundeneMedien = new ArrayList<>();
        for (IMedium medium : medienBestand) {
            if (medium.getTitel().toLowerCase().contains(titel.toLowerCase())) {
                gefundeneMedien.add(medium);
            }
        }
        return gefundeneMedien;
    }

    public List<IMedium> sucheNachAutor(String autor) {
        List<IMedium> gefundeneMedien = new ArrayList<>();
        for (IMedium medium : medienBestand) {
            if (medium.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                gefundeneMedien.add(medium);
            }
        }
        return gefundeneMedien;
    }
}