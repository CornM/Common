package com.zswl.common.api;


import com.zswl.common.base.HttpResult;
import com.zswl.common.util.ToastUtil;

import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class HandleFuc<T> implements Function<HttpResult<T>, T> {


    @Override
    public T apply(HttpResult<T> response) throws Exception {

//        if (response.isOk()) {
//        ToastUtil.showShortToast(response.getMsg());
//            throw new RuntimeException(response.getMsg() + ";" + response.getCode());
//        }

        return response.getData() == null ? (T) new Object() : response.getData();
    }
}
