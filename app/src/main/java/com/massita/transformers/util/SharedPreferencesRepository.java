package com.massita.transformers.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesRepository {

    public static final String SHARED_PREFERENCES = "TRANSFORMER_SHARED_PREFERENCE";
    private static final String SHARED_PREFERENCES_TOKEN = "SHARED_PREFERENCES_TOKEN";

    private SharedPreferences mSharedPreferences;

    public SharedPreferencesRepository(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    public String getToken() {
        return mSharedPreferences.getString(SHARED_PREFERENCES_TOKEN, null);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SHARED_PREFERENCES_TOKEN, token);
        editor.apply();
    }
}
