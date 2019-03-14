package com.example.a707446.alloetudiant.publication.demande;

public interface DataManager {

    void saveAddress(String a);

    void savePrix(Float p);

    void saveDispo(String d);

    void saveTitle(String t);

    void saveDescription(String d);

    void saveMatiere(String m);

    void saveGrade(String g);

    void saveHours(int h);

    String getAddress();

    Float getPrix();

    String getDispo();

    String getTitre();

    String getDescription();

    String getMatiere();

    String getGrade();

    int getHours();
}
