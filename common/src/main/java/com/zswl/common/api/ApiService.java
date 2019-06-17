package com.zswl.common.api;


import com.zswl.common.base.BaseApplication;
import com.zswl.common.util.LogUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
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
    public static String HOST;
    private static OkHttpClient.Builder okHttpClientBuilder;

    private ApiService() {

        retrofit = new Retrofit.Builder().baseUrl(HOST)
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private static class Holder {
        private static ApiService INSTANCE = new ApiService();
    }

    public static void init(BaseApplication baseApplication, ApiServiceOptions options) {
        if (options == null)
            throw new IllegalArgumentException("options is not empty");
        HOST = options.getBaseUrl();
        //init apiservice with options

        File httpCacheDirectory = null;
        Cache cache = null;
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(baseApplication.getCacheDir(), "app_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            }
        } catch (Exception e) {
            LogUtil.d("OKHttp Could not create http cache", e.toString());
        }

        okHttpClientBuilder = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new CaheInterceptor(baseApplication))
                .retryOnConnectionFailure(true)//失败重连
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        for (Interceptor interceptor : options.getInterceptors()) {
            okHttpClientBuilder.addInterceptor(interceptor);
        }


    }


    public static ApiService getInstance() {
        return Holder.INSTANCE;
    }

    public <T> T getApi(Class<T> api) {
        return retrofit.create(api);
    }

}
