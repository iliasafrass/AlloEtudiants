package com.example.a707446.alloetudiant.general;

import android.content.Context;

import lombok.NonNull;

public class SharedPreferencesSingleton {

    public static void setToken(Context context, String token) {
        context
                .getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                .edit()
                .putString("token",token)
                .commit();
    }

    public static String getToken(Context context){
        return context.getApplicationContext()
                .getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                .getString("token","");
    }

    public static void setProfileId(Context context, String profileId) {
        context
                .getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                .edit()
                .putString("profileId",profileId)
                .commit();
    }

    public static String getProfileId(Context context){
        return context
                .getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                .getString("profileId","");
    }

    public static void clear(Context context){
        context
                .getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit();
    }

}
