package com.zswl.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zswl.common.util.RxUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public abstract class BaseFragment extends Fragment {
    protected String TAG = this.getClass().getSimpleName();
    protected BehaviorSubject<RxUtil.LifeEvent> lifeSubject = BehaviorSubject.create();
    protected Context context;
    private Unbinder unbinder;

    //Fragment当前是否可见的状态
    protected View mRootView;
    protected boolean isVisible=true;    //判断Fragment是否可见
    private boolean isPrepared;     //判断控件是否已经做好绑定工作 防止在initData()时报空指针错误
    protected boolean isFirst = true;    //判断Fragment是否是第一次加载

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        lifeSubject.onNext(RxUtil.LifeEvent.CREATE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();    //Fragment可见时调用的方法
        } else {
            isVisible = false;
            onInVisible();   //Fragment不可见是调用
        }
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        init(mRootView);
        isFirst = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {    //避免多次重复加载
            mRootView = inflater.inflate(getViewId(), container, false);
            unbinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifeSubject.onNext(RxUtil.LifeEvent.DESTROY);
        unbinder.unbind();
    }

    protected abstract int getViewId();

    protected abstract void init(View view);

    /**
     * 绑定生命周期
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> getLifeObservable(Observable<HttpResult<T>> observable) {


        return observable.compose(RxUtil.io_main(lifeSubject));
    }

}
