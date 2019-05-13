package com.zswl.baseprojectdemo;

import com.zswl.common.base.HttpResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("apiindex/getShangjiaBuTypeId")
    Observable<HttpResult<List<TestBean>>> getTestData(@Field("start") int start,
                                                       @Field("limit") int limit);

}
