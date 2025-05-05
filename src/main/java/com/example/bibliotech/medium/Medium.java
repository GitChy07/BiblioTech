package com.example.bibliotech.medium;

public abstract class Medium {
    protected String name;
    protected int erscheinungsjahr;
    protected String autor;

    // Konstruktor
    public Medium(String name, int erscheinungsjahr, String autor) {
        this.name = name;
        this.erscheinungsjahr = erscheinungsjahr;
        this.autor = autor;
    }

    // Getter-Methoden
    public String getName() {
        return name;
    }

    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public String getAutor() {
        return autor;
    }

    // Abstrakte Methode – muss von Unterklassen implementiert werden
    public abstract String getTyp();

    // Gemeinsame Darstellung
    @Override
    public String toString() {
        return getTyp() + ": \"" + name + "\" (" + erscheinungsjahr + "), " + autor;
    }
}
