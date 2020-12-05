package com.proyectofinal.homelife.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.proyectofinal.homelife.R;

import static android.content.Context.MODE_PRIVATE;

public class HomeLifePreference {
    private final Context mContext;
    private static final String TOKEN = "TOKEN";
    private static final String USER = "USER";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_ID = "USER_NAME_ID";


    public HomeLifePreference(Context context) {
        this.mContext = context;
    }

    public SharedPreferences getSharedPreferences() {
        return this.mContext.getSharedPreferences(mContext.getString(R.string.app_name), MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditoSharedPreference() {
        return getSharedPreferences().edit();
    }

    public String getToken() {
        return getString(TOKEN);
    }

    public void setToken(String value) {
        putString(TOKEN, value);
    }

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String value) {
        putString(USER_ID, value);
    }

    public String getUserName() {
        return getString(USER_NAME);
    }

    public void setUserName(String value) {
        putString(USER_NAME, value);
    }

    public String getString(String key) {
        SharedPreferences sharedPref = getSharedPreferences();
        return sharedPref.getString(key, "");
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = getEditoSharedPreference();
        editor.putString(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        SharedPreferences sharedPref = getSharedPreferences();
        return sharedPref.getInt(key, -1);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = getEditoSharedPreference();
        editor.putInt(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        SharedPreferences sharedPref = getSharedPreferences();
        return sharedPref.getBoolean(key, false);
    }

    public void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = getEditoSharedPreference();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public long getLong(String psKey) {
        SharedPreferences sharedPref = getSharedPreferences();
        return sharedPref.getLong(psKey, -1);
    }

    public void putLong(String psKey, long psValue) {
        SharedPreferences.Editor editor = getEditoSharedPreference();
        editor.putLong(psKey, psValue);
        editor.apply();
    }

    public void clear() {
        SharedPreferences.Editor editor = getEditoSharedPreference();
        editor.clear();
        editor.apply();
    }
}