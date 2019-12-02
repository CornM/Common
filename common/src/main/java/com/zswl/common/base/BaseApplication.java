package com.zswl.common.base;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import java.util.Stack;

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static BaseApplication instance;
    private Stack<Activity> allActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityLifecycleCallbacks(this);
        allActivities = new Stack<>();
    }

    public static BaseApplication getAppInstance() {
        return instance;
    }

    public Activity getCurrentActivity() {
        return allActivities.lastElement();
    }

    public void addActivity(Activity act) {
        if (allActivities != null) {
            allActivities.add(act);
        }

    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void finishAllActivity() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
    }

    public void exitApp() {
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        addActivity(activity);
        setStatusBarColor(activity.getWindow(), Color.WHITE, true);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeActivity(activity);
    }

    public static void setStatusBarColor(@NonNull Window window, @ColorInt int color, boolean colorIsLight) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            window.setStatusBarColor(color);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();

            if (colorIsLight) {

                window.getDecorView().setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            }else {

                window.getDecorView().setSystemUiVisibility(systemUiVisibility ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            }

        }

    }


}
