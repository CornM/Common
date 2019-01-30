package com.zswl.common.api;


import com.zswl.common.base.HttpResult;

import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class HandleFuc<T> implements Function<HttpResult<T>, T> {


    @Override
    public T apply(HttpResult<T> response) throws Exception {

        if (!response.isOk()) {
            throw new RuntimeException(response.getCode() + "" + response.getMsg() != null ? response.getMsg() : "");
        }

        return response.getData();
    }
}
