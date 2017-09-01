package com.alc.ljv.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by OLORIAKE KEHINDE on 8/29/2017.
 */

public class UtilsClass {
    public static final String KEY_LOCATION = "location";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_SORT = "sort";
    public static final String KEY_CHANGE_STATUS = "change_status";
    // Sharedpref file
    private static final String PREF_NAME = "DevSearchPref";
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    Context _context;


    public UtilsClass(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void storeFilterParameters(String location, String language, String sorting_paramenter) {
        editor.putString(KEY_LOCATION, location);
        editor.putString(KEY_LANGUAGE, language);
        editor.putString(KEY_SORT, sorting_paramenter);
        editor.commit();

    }

    public void storeFilterChange(boolean changeStatus) {
        editor.putBoolean(KEY_CHANGE_STATUS, changeStatus);
        editor.commit();

    }

    public boolean getFilterStatus() {
        return pref.getBoolean(KEY_CHANGE_STATUS, false);


    }

    public String getLocation() {
        return pref.getString(KEY_LOCATION, null);

    }

    public String getLanguage() {
        String user = pref.getString(KEY_LANGUAGE, null);
        return user;
    }

    public String getSort() {
        return pref.getString(KEY_SORT, null);

    }


}
