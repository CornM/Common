package com.zswl.baseprojectdemo;

import com.zswl.common.api.ApiService;
import com.zswl.common.api.ApiServiceOptions;
import com.zswl.common.base.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        ApiServiceOptions options = new ApiServiceOptions("http://app.xunbaowang.net/xunbao/");
        ApiService.init(this, options);

    }


}
