package com.example.bibliotech.medium;

public abstract class AbstractMedium implements IMedium {
    private String titel;
    private String autor;
    private int erscheinungsjahr;
    private boolean istAusgeliehen;

    public AbstractMedium(String titel, String autor, int erscheinungsjahr) {
        this.titel = titel;
        this.autor = autor;
        this.erscheinungsjahr = erscheinungsjahr;
        this.istAusgeliehen = false;
    }

    @Override
    public String getTitel() {
        return titel;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    @Override
    public boolean istAusgeliehen() {
        return istAusgeliehen;
    }

    @Override
    public void setAusgeliehen(boolean ausgeliehen) {
        istAusgeliehen = ausgeliehen;
    }
}