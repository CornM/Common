package com.zswl.common.api;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Interceptor;

public class ApiServiceOptions {
    private String baseUrl;
    private List<Interceptor> interceptors;

    public ApiServiceOptions(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl))
            throw new IllegalArgumentException("baseUrl is empty, you must set baseUrl ");
        this.baseUrl = baseUrl;
        interceptors = new ArrayList<>();
    }

    /**
     * 设置拦截器
     *
     * @param interceptor
     */
    public void setInterceptors(Interceptor... interceptor) {
        if (interceptor != null) {
            interceptors.addAll(Arrays.asList(interceptor));
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }


    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

}
