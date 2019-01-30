package com.zswl.common.util;

import android.util.Log;

import com.zswl.common.BuildConfig;


/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class LogUtil {
    private static final String TAG = "LogUtil";
    public static boolean isDebug = BuildConfig.DEBUG;

    public static void d(String TAG, String s) {
        if (isDebug)
            Log.d(TAG, "-------------->" + s);
    }

    public static void d( String s) {
        if (isDebug)
            Log.d(TAG, "-------------->" + s);
    }
}
