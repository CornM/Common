package com.zswl.baseprojectdemo;

import com.zswl.common.api.ApiService;
import com.zswl.common.api.ApiServiceOptions;
import com.zswl.common.api.UserIdInterceptor;
import com.zswl.common.base.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiServiceOptions options = new ApiServiceOptions("he/http://192.168.2.180:8080/aixunc");
        options.setInterceptors(new UserIdInterceptor());
        ApiService.init(this, options);
    }
}
