package com.example.faithfully;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceSettings {

    private static final String PREF_FILE = "settings_pref";
    public static final String RELIGION_TYPE = "religion_type";

    static void saveReligionToPref(Context context, String religion) {
        final SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(RELIGION_TYPE, religion);
        editor.apply();
    }

    static String getReligionType(Context context) {
        final SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        final String defaultValue = null;
        return sharedPref.getString(RELIGION_TYPE, null);
    }
}
