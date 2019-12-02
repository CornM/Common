package com.zswl.common.api;

import android.text.TextUtils;

import com.zswl.common.util.SpUtil;

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
//
//        //对应请求头大伙按照自己的传输方式 定义
//        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), parameterStr);
//        request = request.newBuilder().patch(requestBody).build();


//        RequestBody requestBody = oldRequest.body();
//        LogUtil.d("UserIdInterceptor body:"+bodyToString(requestBody));

        String userId = SpUtil.getUserId();
        if (!TextUtils.isEmpty(userId)) {
//            if (oldRequest.body() instanceof FormBody) {
//                FormBody oldFormBody = (FormBody) oldRequest.body();
//                FormBody.Builder builder = new FormBody.Builder().add(Constant.USERID, "1234567");
//                for (int i = 0; i < oldFormBody.size(); i++) {
//                    builder.add(oldFormBody.name(i), oldFormBody.value(i));
//                }
//                oldRequest = oldRequest.newBuilder()
//                        .headers(oldRequest.headers())
//                        .method(oldRequest.method(), builder.build()).build();
//            }


            HttpUrl newUrl = oldRequest.url()
                    .newBuilder()
                    .addQueryParameter(Constant.USERID, userId).build();
            oldRequest = oldRequest.newBuilder().url(newUrl).build();


        }

//        LogUtil.d("UserIdInterceptor body:" + oldRequest.toString());

//
//        oldRequest = oldRequest.newBuilder()
//                .addHeader("Content-Type","application/json;charset=UTF-8")
//                .build();

        return chain.proceed(oldRequest);
    }


}
