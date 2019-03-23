package com.zswl.baseprojectdemo;

import com.zswl.common.base.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        HOST = "http://192.168.2.180:8080/aixunche/";
    }
}
