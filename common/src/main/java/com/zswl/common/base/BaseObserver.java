package com.zswl.common.base;


import android.content.Context;

import com.zswl.common.api.ExceptionHandle;
import com.zswl.common.util.ToastUtil;
import com.zswl.common.widget.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/6/11 0011.
 * 基础网络回调
 * 处理返回结果，网络异常，解析异常
 */

public abstract class BaseObserver<T> implements Observer<T> {
    private Context context;
    private LoadingDialog loadingDialog;
    private boolean isShow;
    private T data;

    public BaseObserver(Context context) {
        this(context, true);
    }

    public BaseObserver(Context context, boolean isShow) {
        this.context = context;
        this.isShow = isShow;
        if (isShow)
            loadingDialog = new LoadingDialog(context);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (loadingDialog != null)
            loadingDialog.show();
    }

    @Override
    public void onNext(T t) {
        data = t;
        receiveResult(t);
    }

    @Override
    public void onError(Throwable e) {
        if (loadingDialog != null)
            loadingDialog.dismiss();
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onComplete() {
        if (loadingDialog != null)
            loadingDialog.dismiss();
    }

    public void onError(ExceptionHandle.ResponeThrowable e) {
        ToastUtil.showShortToast(e.message);

    }


    public abstract void receiveResult(T result);

    public T getData() {
        return data;
    }

}
