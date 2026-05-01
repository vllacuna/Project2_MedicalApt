package com.example.medicalapt.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.medicalapt.data.User;

public class SessionSharedPref {

    public static final String PREF_NAME = "medical_apt_session";
    public static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_ADMIN = "is_admin";

    public static void logIn(Context context, User user) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit()
                .putBoolean(KEY_IS_LOGGED_IN, true)
                .putString(KEY_USERNAME, user.getUsername())
                .putBoolean(KEY_IS_ADMIN, user.isAdmin())
                .apply();
    }

    public static void logOut(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public static String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USERNAME, "Guest");
    }

    public static boolean isAdmin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_IS_ADMIN, false);
    }
}
