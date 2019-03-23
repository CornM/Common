package com.zswl.common.util;


import com.zswl.common.api.HandleFuc;
import com.zswl.common.api.HttpResponseFunc;
import com.zswl.common.base.HttpResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class RxUtil {
    private static <T> ObservableTransformer<T, T> bindUntilEvent(Observable<LifeEvent> lifeSubject) {
        //被监视的Observable
        final Observable<LifeEvent> observable = lifeSubject.filter(new Predicate<LifeEvent>() {

            @Override
            public boolean test(LifeEvent activityEvent) throws Exception {
                return activityEvent.equals(LifeEvent.DESTROY);
            }
        });
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.map((Function<? super T, ? extends T>) new HandleFuc<T>())
                        .onErrorResumeNext(new HttpResponseFunc<T>())
                        .takeUntil(observable);
            }
        };

    }


    public static ObservableTransformer io_main(final Observable<LifeEvent> lifeSubject) {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {

                return upstream.compose(bindUntilEvent(lifeSubject))
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public static <T> ObservableTransformer<HttpResult<T>, T> io_mainWithoutLife() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {

                return upstream.map(new HandleFuc<T>())
                        .onErrorResumeNext(new HttpResponseFunc<T>())
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public static ObservableTransformer io_main_nolife() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {

                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public static ObservableTransformer bindLifeNoMap(Observable<LifeEvent> lifeSubject ) {
        //被监视的Observable
        final Observable<LifeEvent> observable = lifeSubject.filter(new Predicate<LifeEvent>() {

            @Override
            public boolean test(LifeEvent activityEvent) throws Exception {
                return activityEvent.equals(LifeEvent.DESTROY);
            }
        });
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {

                return upstream
                        .takeUntil(observable)
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public enum LifeEvent {
        CREATE,
        DESTROY
    }
}
