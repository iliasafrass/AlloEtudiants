package com.example.a707446.alloetudiant.general;

import com.example.a707446.alloetudiant.connexion.Login;

public class SharedPreferencesHelper {

    public static void setToken(String value){
        Login.preferences.edit().putString("token",value).commit();
    }

    public static String getToken() {
        return Login.preferences.getString("token","");
    }

    public static void setProfileId(String value){
        Login.preferences.edit().putString("profileId",value).commit();
    }

    public static String getProfileId() {
        return Login.preferences.getString("profileId","");
    }
}
