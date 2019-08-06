package com.zswl.common.base;

import android.content.Context;

import com.zswl.common.util.RxUtil;

import io.reactivex.subjects.BehaviorSubject;

public class BaseWebJs {
    protected Context context;
    protected BehaviorSubject<RxUtil.LifeEvent> lifeEvent;
    protected JsClickListener listener;

    public void setListener(JsClickListener listener) {
        this.listener = listener;
    }

    public BaseWebJs(Context context, BehaviorSubject<RxUtil.LifeEvent> lifeEvent) {
        this.context = context;
        this.lifeEvent = lifeEvent;
    }


    public interface JsClickListener {
        void onJsClick(String... str);
    }


}
