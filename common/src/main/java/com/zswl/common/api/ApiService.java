package com.zswl.common.api;


import com.zswl.common.base.BaseApplication;
import com.zswl.common.util.LogUtil;
import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/6/9 0009.
 */

public class ApiService {
    private static final int DEFAULT_TIMEOUT = 20;
    private final Retrofit retrofit;

    private ApiService() {
        File httpCacheDirectory = null;
        Cache cache = null;
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(BaseApplication.getAppInstance().getCacheDir(), "app_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            }
        } catch (Exception e) {
            LogUtil.d("OKHttp Could not create http cache", e.toString());
        }
        //okhttp创建了
        //失败重连
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new CaheInterceptor(BaseApplication.getAppInstance()))
//                .addNetworkInterceptor(new CaheInterceptor(App.getAppInstance()))
//                .addInterceptor(new UserIdInterceptor())
                .addInterceptor(new LogInterceptor())
                .retryOnConnectionFailure(true)//失败重连
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder().baseUrl(BaseApplication.HOST)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


    }

    private static class Holder {
        private static ApiService INSTANCE = new ApiService();
    }

    public static ApiService getInstance() {
        return Holder.INSTANCE;
    }

    public <T> T getApi(Class<T> api) {
        return retrofit.create(api);

    }


}
