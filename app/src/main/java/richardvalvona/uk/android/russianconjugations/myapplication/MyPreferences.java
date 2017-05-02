package richardvalvona.uk.android.russianconjugations.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by richard on 04/11/16.
 */

public final class MyPreferences {

    public final static String DATA_PROPERLY_IN_DATABASE = "DATA_PROPERLY_IN_DATABASE";

    private static MyPreferences instance = null;

    private final String PREFS_FILE_NAME = "1958382_prefs";

    private MyPreferences() {}

    public void saveString(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void saveInt(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveLong(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public String getString(Context context, String key, String def) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(key, def);
    }

    public int getInt(Context context, String key, int def) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, def);
    }

    public boolean getBoolean(Context context, String key, boolean def) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, def);
    }

    public long getLong(Context context, String key, long def) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getLong(key, def);
    }

    public static MyPreferences getInstance() {

        if (instance == null) {
            instance = new MyPreferences();
        }

        return instance;
    }
}
