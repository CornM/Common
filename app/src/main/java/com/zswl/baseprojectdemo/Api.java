package com.zswl.baseprojectdemo;

import com.zswl.common.base.HttpResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {


    @FormUrlEncoded
    @POST("apicishan/allCiShanTuiJian")
    Observable<HttpResult<List<TestBean>>> getTestData(@Field("start") int start,
                                                       @Field("limit") int limit);

    @FormUrlEncoded
    @POST("apiowner/userList")
    Observable<HttpResult<List<UserListBean>>> userList(@Field("userId") String userId);

    @Multipart
    @POST("userAppApi/saveUpdate")
    Observable<HttpResult<Object>> uploadImg(@Part("userId") RequestBody userId,
                                             @Part MultipartBody.Part part);

}
