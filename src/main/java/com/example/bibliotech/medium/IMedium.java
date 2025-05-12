package com.example.bibliotech.medium;

public interface IMedium {
    String getTitel();
    String getAutor();
    int getErscheinungsjahr();
    boolean istAusgeliehen();
    void setAusgeliehen(boolean ausgeliehen);
}
