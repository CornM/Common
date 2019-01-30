package com.zswl.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zswl.common.util.InputManagerUtil;
import com.zswl.common.util.RxUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Administrator on 2018/6/9 0009.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();
    protected Context context;
    protected BehaviorSubject<RxUtil.LifeEvent> lifeSubject = BehaviorSubject.create();
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeSubject.onNext(RxUtil.LifeEvent.CREATE);
        context = this;
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        InputManagerUtil.getInstances(context).hideALlSoftInput();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifeSubject.onNext(RxUtil.LifeEvent.DESTROY);
        unbinder.unbind();
        lifeSubject = null;
    }

    protected abstract int getLayoutId();

    protected abstract void init();



}
