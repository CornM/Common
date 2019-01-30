package com.zswl.common.base;

import android.content.Context;

import com.zswl.common.util.RxUtil;

import io.reactivex.subjects.BehaviorSubject;

public class BaseWebJs {
    protected Context context;
    protected BehaviorSubject<RxUtil.LifeEvent> lifeEvent;

    public BaseWebJs(Context context, BehaviorSubject<RxUtil.LifeEvent> lifeEvent) {
        this.context = context;
        this.lifeEvent = lifeEvent;
    }



}
