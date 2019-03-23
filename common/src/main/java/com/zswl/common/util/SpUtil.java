package com.zswl.common.util;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class SpUtil {
    private static SharedPreferences preference;
    private static SharedPreferences.Editor editor;

    private SpUtil() {
    }

    public static void init(Context context, String name) {
        preference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = preference.edit();
    }

    public static void putValue(String key, Object value) {
        if (value == null)
            return;
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else {
            editor.putInt(key, (Integer) value);
        }
        editor.commit();

    }

    public static String getValue(String key) {
        return preference.getString(key, "");
    }

    public static int getInt(String key) {
        return preference.getInt(key, -1);
    }


    public static void clearSP() {
        editor.clear().commit();
    }
}
