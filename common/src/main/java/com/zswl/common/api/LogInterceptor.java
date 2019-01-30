package com.zswl.common.api;


import com.zswl.common.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by Administrator on 2018/7/10 0010.
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtil.d(String.format("%1$s->%2$s",request.method(),request.url()));

        if(request.body()!=null){
            LogUtil.d("RequestBody:" + bodyToString(request.body()));
        }

        Response response = chain.proceed(request);
        MediaType mediaType = response.body().contentType();
        String responseBody = response.body().string();
        LogUtil.d("ResponseBody:" + responseBody);

        return response.newBuilder()
                .body(ResponseBody.create(mediaType, responseBody))
                .build();
    }

    private String bodyToString(final RequestBody request) {
        if(request!=null){
            try {
                final RequestBody copy = request;
                final Buffer buffer = new Buffer();
                copy.writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                LogUtil.d("Did not work.");
            }
        }
        return null;
    }

}
