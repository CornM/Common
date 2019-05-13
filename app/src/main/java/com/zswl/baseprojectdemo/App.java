package com.zswl.baseprojectdemo;

import com.zswl.common.api.ApiService;
import com.zswl.common.api.ApiServiceOptions;
import com.zswl.common.api.UserIdInterceptor;
import com.zswl.common.base.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        ApiServiceOptions options = new ApiServiceOptions("http://47.93.16.37:80/delivery/");
//        options.setInterceptors(new UserIdInterceptor());
        ApiService.init(this, options);
    }
}
