package com.zswl.common.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class UserIdInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();


//        if (!TextUtils.isEmpty(SpUtil.getUserId())) {
//            if (oldRequest.body() instanceof FormBody) {
//
//
//                FormBody oldFormBody = (FormBody) oldRequest.body();
//                FormBody.Builder builder = new FormBody.Builder().add(Constant.USERID, SpUtil.getUserId());
//                for (int i = 0; i < oldFormBody.size(); i++) {
//
//                    builder.add(oldFormBody.name(i), oldFormBody.value(i));
//                }
//                oldRequest = oldRequest.newBuilder()
//                        .headers(oldRequest.headers())
//                        .method(oldRequest.method(),builder.build()).build();
//            }


        HttpUrl newUrl = oldRequest.url().newBuilder().build();
//                    .addQueryParameter(Constant.USERID,
//                            SpUtil.getUserId()).build();
        oldRequest = oldRequest.newBuilder().url(newUrl).build();

//        }

        return chain.proceed(oldRequest);
    }
}
