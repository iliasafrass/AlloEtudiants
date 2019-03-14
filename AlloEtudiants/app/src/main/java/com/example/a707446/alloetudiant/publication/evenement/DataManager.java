package com.example.a707446.alloetudiant.publication.evenement;

public interface DataManager {
    void saveAddress(String a);

    void saveDates(String d);

    void saveTitle(String t);

    void saveDescription(String d);

    String getAddress();

    String getDates();

    String getTitre();

    String getDescription();
}
