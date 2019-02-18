package com.example.a707446.alloetudiant.publication.proposition.pageFragments;

public interface DataManager {
    void saveAddress(String a);
    void savePrix(String p);
    void saveDispo(String d);
    void saveTitle(String t);
    void saveDescription(String d);
    void saveMatiere(String m);

    String getAddress();
    String getPrix();
    String getDispo();
    String getTitre();
    String getDescription();
    String getMatiere();
}
