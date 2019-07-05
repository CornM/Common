package com.zswl.baseprojectdemo;

import android.net.Uri;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideCustomImageLoader;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.loader.glide.GlideModel;
import com.zswl.common.api.ApiService;
import com.zswl.common.api.ApiServiceOptions;
import com.zswl.common.api.LogInterceptor;
import com.zswl.common.api.UserIdInterceptor;
import com.zswl.common.base.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        ApiServiceOptions options = new ApiServiceOptions("http://39.98.255.39/domestic/");
        ApiService.init(this, options);

    }


}
