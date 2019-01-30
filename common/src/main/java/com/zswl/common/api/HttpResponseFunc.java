package com.zswl.common.api;



import com.zswl.common.util.LogUtil;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/6/11 0011.
 * 传递异常观察者
 */

public class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        LogUtil.d("------------error HttpResponseFunc----------" + throwable.getMessage());
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}
